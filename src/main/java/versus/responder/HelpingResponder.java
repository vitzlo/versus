package versus.responder;

import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import versus.controller.content.EmbedContent;
import versus.controller.content.VContent;

import java.util.Map;

public abstract class HelpingResponder implements Responder {
    protected final Map<String, String> helpMessages;
    protected final String helpTitle;

    protected HelpingResponder(Map<String, String> helpMessages, String helpTitle) {
        this.helpMessages = helpMessages;
        this.helpTitle = helpTitle;
    }

    protected VContent helpEmbed(String prefix) {
        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder()
                .color(Color.of(defaultHexCode()))
                .title(helpTitle);

        for (Map.Entry<String, String> message : helpMessages.entrySet()) {
            builder.addField(String.format("`%s%s`", prefix, message.getKey()),
                    "↳ " + message.getValue(), false);
        }

        return new EmbedContent(builder.build());
    }
}
