package versus.responder;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;
import versus.controller.content.EmptyContent;
import versus.controller.content.VContent;
import versus.data.DataAccess;
import versus.model.TestResponder;
import versus.model.clash.ClashResponder;

import java.util.List;

public class DefaultResponseManager implements ResponseManager {
    private final List<Responder> responders;
    private final DiscordClient client;
    private final DataAccess dataAccess = new DataAccess();

    public DefaultResponseManager(DiscordClient client) {
        this.client = client;
        this.responders = List.of(new TestResponder(), new ClashResponder());
    }

    @Override
    public VContent respond(Message message) {
        if (message.getGuildId().isEmpty()) {
            return respondDM(message);
        }

        String text = message.getContent();
        String prefix = dataAccess.getGuildPrefix(message.getGuildId().get());
        VContent response;

        for (Responder responder : responders) {
            if (responder.requiresPrefix() && text.startsWith(prefix)) {
                response = responder.printReply(message, prefix.length() == text.length() ? "" : text.substring(prefix.length()));
            } else if (!responder.requiresPrefix()) {
                response = responder.printReply(message, text);
            } else {
                continue;
            }
            if (!response.isEmpty()) {
                return response;
            }
        }

        return new EmptyContent();
    }

    private VContent respondDM(Message message) {
        return new EmptyContent(); // TODO: implement
    }
}
