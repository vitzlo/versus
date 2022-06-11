package versus.controller.content;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.reactivestreams.Publisher;

import java.util.function.Function;

public class EmbedContent implements VContent {
    private final EmbedCreateSpec content;

    public EmbedContent(EmbedCreateSpec content) {
        this.content = content;
    }

    @Override
    public Function<Message, Publisher<Message>> publisher() {
//        return message -> message.getChannel().flatMap(channel -> channel.createMessage(MessageCreateSpec.builder()
//                .addEmbed(content).build()));
        return message -> message.getChannel().flatMap(channel -> channel.createMessage(content));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
