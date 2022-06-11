package versus.controller.content;

import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class EmptyContent implements VContent {
    @Override
    public Function<Message, Publisher<Message>> publisher() {
        return message -> Mono.empty();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
