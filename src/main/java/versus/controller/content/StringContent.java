package versus.controller.content;

import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;

import java.util.function.Function;

public class StringContent implements VContent {
    private final String content;

    public StringContent(String content) {
        this.content = content;
    }

    @Override
    public Function<Message, Publisher<Message>> publisher() {
        return message -> message.getChannel().flatMap(channel -> channel.createMessage(content));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
