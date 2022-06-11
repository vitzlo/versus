package versus.controller;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import versus.controller.content.VContent;
import versus.responder.DefaultResponseManager;
import versus.responder.ResponseManager;

public class D4JController implements VersusController {
    private final DiscordClient client;
    private final ResponseManager manager;

    public D4JController(DiscordClient client) {
        this.client = client;
        manager = new DefaultResponseManager(client);
    }

    public void run() {
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event -> initLogin(event)).then();
            Mono<Void> readNewMessage = gateway.on(MessageCreateEvent.class, event -> readMessage(event)).then();

            return printOnLogin.and(readNewMessage);
        });

        login.block();
    }

    private Publisher<Object> initLogin(ReadyEvent event) {
        return Mono.fromRunnable(() -> {
            final User self = event.getSelf();
            System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
        });
    }

    private Publisher<Message> readMessage(MessageCreateEvent event) {
        Message message = event.getMessage();

        VContent response = manager.respond(message);
        return response.publisher().apply(message);
    }
}
