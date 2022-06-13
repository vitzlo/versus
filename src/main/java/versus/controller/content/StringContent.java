package versus.controller.content;

import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import versus.Ut;
import versus.data.DataAccess;

import java.util.function.Function;

public class StringContent implements VContent {
    private final String content;
    private final DataAccess dataAccess = new DataAccess();

    public StringContent(String content) {
        this.content = content;
    }

    @Override
    public Function<Message, Publisher<Message>> publisher() {
        return message -> message.getChannel().flatMap(channel -> channel.createMessage(formPlaceholders(message, content)));
    }

    private String formPlaceholders(Message message, String content) {
        if (content.contains(Ut.PLACEHOLDER_PREFIX)) {
            if (message.getGuildId().isPresent()) {
                content = content.replaceAll(Ut.PLACEHOLDER_PREFIX, dataAccess.getGuildPrefixElseDefault(message.getGuildId().get().asString()));
            } else {
                content = content.replaceAll(Ut.PLACEHOLDER_PREFIX, Ut.DEFAULT_PREFIX);
            }
        }

        return content;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
