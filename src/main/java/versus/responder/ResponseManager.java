package versus.responder;

import discord4j.core.object.entity.Message;
import versus.controller.content.VContent;

public interface ResponseManager {
    VContent respond(Message message);
}
