package versus.model;

import discord4j.core.object.entity.Message;
import versus.controller.content.EmptyContent;
import versus.controller.content.StringContent;
import versus.controller.content.VContent;
import versus.responder.Responder;

public class TestResponder implements Responder {
    @Override
    public boolean requiresPrefix() {
        return false;
    }

    @Override
    public VContent printReply(Message message, String text) {
        if (text.equals("ping")) {
            return new StringContent("pong");
        } else {
            return new EmptyContent();
        }
    }
}
