package versus.responder;

import discord4j.core.object.entity.Message;
import versus.controller.content.VContent;

public interface Responder {
    boolean requiresPrefix();

    int defaultHexCode();

    VContent printReply(Message message, String text);
}
