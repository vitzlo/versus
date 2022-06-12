package versus.model.clash;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import versus.ApiUt;
import versus.FileUt;
import versus.Ut;
import versus.controller.content.EmbedContent;
import versus.controller.content.EmptyContent;
import versus.controller.content.StringContent;
import versus.controller.content.VContent;
import versus.data.DataAccess;
import versus.model.clash.player.ClashPlayer;
import versus.responder.HelpingResponder;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ClashResponder extends HelpingResponder {
    private final List<String> prefixes = List.of("clash", "coc");
    private final DataAccess dataAccess = new DataAccess();

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
        String userId = message.getUserData().id().asString();
        if (text.isEmpty() || text.equals("help")) {
            return helpEmbed();
        } else if (text.matches("init #[0-Z]+ [0-z]+")) {
            return initializeUser(userId, splits[1], splits[2]);
        } else {
            return retrieveStats(message, userId, text);
        }
    }

    private VContent initializeUser(String userId, String playerTag, String apiToken) {
        return new EmptyContent(); // TODO: implement
    }

    private VContent retrieveStats(Message message, String userId, String text) {
        ClashPlayer player;
        User user = message.getAuthor().get(); // TODO: ???

        try {
            player = generatePlayer(userId);
        } catch (Exception e) {
            return new StringContent(e.getMessage());
        }

        if (text.matches("player")) {
            return playerGeneralInfo(player, user);
        } else {
            return new EmptyContent();
        }
    }

    private VContent playerGeneralInfo(ClashPlayer player, User user) {
        EmbedCreateSpec builder = EmbedCreateSpec.builder()
                .color(Color.of(defaultHexCode()))
                .title(String.format("%s (Level %s)", player.name, player.expLevel))
                .author(user.getUsername(), "https://github.com/vitzlo/versus", user.getAvatarUrl()) // TODO: ut for more specific builder?
                .description("Player tag: " + player.tag)
                .thumbnail(player.league.iconUrls.get("medium"))

                .addField("Town Hall Level", "" + player.townHallLevel, true)
                .addField("Town Hall Weapon Level", "" + player.townHallWeaponLevel, true)
                .addField("\u200B", "\u200B", false)
                .addField("Trophies", "" + player.trophies, true)
                .addField("Best Trophies", "" + player.bestTrophies, true)
                .addField("\u200B", "\u200B", false)
                .addField("Attack Wins", "" + player.attackWins, true)
                .addField("Defense Wins", "" + player.defenseWins, true)
                .addField("\u200B", "\u200B", false)
                .addField("Clan Name", player.clan.name, true)
                .addField("Clan Tag", player.clan.tag, true)
                .addField("\u200B", "\u200B", false)
                .addField("Donations Received", "" + player.donationsReceived, true)
                .addField("Donations Given", "" + player.donations, true)
                .timestamp(Instant.now())
                .footer("versus", Ut.PFP_LINK)
                .build();

        return new EmbedContent(builder);
    }

    private ClashPlayer generatePlayer(String userId) throws IOException, IllegalArgumentException {
        String playerTag;

        try {
            playerTag = dataAccess.getClashPlayerTag(userId);
        } catch (IOException io) {
            throw new IOException(FileUt.DB_ERROR_MSG); // TODO: server prefix via data access
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("User not found in the database. Use `<prefix>clash help` to learn about registering."); // TODO: server prefix via data access
        }

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(String.format("https://api.clashofclans.com/v1/players/%s", ApiUt.encodeUrl(playerTag)))
                    .header("Authorization", "Bearer " + dataAccess.getClashToken())
                    .build();

            Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            return gson.fromJson(response.body().string(), ClashPlayer.class);
        } catch (IOException io) {
            throw new IOException(ApiUt.IO_ERROR_MSG);
        } catch (RuntimeException rte) {
            throw new RuntimeException(ApiUt.BAD_JSON_MSG);
        }
    }
}
