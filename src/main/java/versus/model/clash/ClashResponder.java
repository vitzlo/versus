package versus.model.clash;

import discord4j.core.object.entity.Message;
import versus.Ut;
import versus.controller.content.EmptyContent;
import versus.controller.content.VContent;
import versus.responder.HelpingResponder;

import java.util.List;
import java.util.Map;

public class ClashResponder extends HelpingResponder {
    private final List<String> prefixes = List.of("clash", "coc");

    public ClashResponder() {
        super(Map.of("clash/coc init <#tag> <apiKey>", "↳ Register your Discord account with the bot via the player tag and the API key found in the game settings"), "Clash of Clans Help");
    }

    @Override
    public boolean requiresPrefix() {
        return true;
    }

    @Override
    public int defaultHexCode() {
        return 0xffe001;
    }

    @Override
    public VContent printReply(Message message, String text) {
        try {
            text = Ut.pruneCommand(prefixes, text, true);
        } catch (IllegalArgumentException iae) {
            return new EmptyContent();
        }

        String[] splits = text.split(" ");
        if (text.isEmpty() || text.equals("help")) {
            return helpEmbed();
        } else if (text.matches("init #[0-Z]+ [0-z]+")) {
            String userId = message.getUserData().id().asString();

            return initializeUser(userId, splits[1], splits[2]);
        } else {
            return retrieveStats(message, splits);
        }
    }

    private VContent initializeUser(String userId, String playerTag, String apiToken) {
        return new EmptyContent(); // TODO: implement
    }

    private VContent retrieveStats(Message message, String[] splits) {
        return new EmptyContent(); // TODO: implement
    }
}
