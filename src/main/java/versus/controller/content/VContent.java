package versus.controller.content;

import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;

import java.util.function.Function;

public interface VContent {
    Function<Message, Publisher<Message>> publisher();

    boolean isEmpty();
}
