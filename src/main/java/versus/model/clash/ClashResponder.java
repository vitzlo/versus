package versus.model.clash;

import com.google.gson.Gson;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import okhttp3.*;
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
        super(Map.of("clash/coc init <#tag> <apiKey>", "Register your Discord account with the bot via the player tag and the API key found in the game settings",
                        "clash/coc player", "Get general information about your account"),
                "Clash of Clans Help");
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
        String prefix = Ut.getMessageGuildPrefix(message);
        if (text.isEmpty() || text.equals("help")) {
            return helpEmbed(prefix);
        } else if (text.matches("init #[0-Z]+ [0-z]+")) {
            return initializeUser(userId, splits[1], splits[2]);
        } else {
            return retrieveStats(message, userId, text);
        }
    }

    private VContent initializeUser(String userId, String playerTag, String apiToken) {
        try {
            if (validatePlayer(playerTag, apiToken)) { // TODO: more accurate error messages for IO?
                if (dataAccess.hasClashUserId(userId) && dataAccess.getClashPlayerTag(userId).equals(playerTag)) {
                    return new StringContent("This player tag is already registered with this user. No changes have been made.");
                } else if (dataAccess.hasClashUserId(userId)) {
                    dataAccess.setClashPlayerTag(userId, playerTag);
                    return new StringContent("Success! The previous player tag was overwritten.");
                } else {
                    dataAccess.setClashPlayerTag(userId, playerTag);
                    return new StringContent("Success! The player tag was registered.");
                }
            } else {
                return new StringContent("Token could not be validated. Try checking the parameters or refreshing the app.");
            }
        } catch (IOException io) {
            return new StringContent(ApiUt.IO_ERROR_MSG);
        }
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
                .title(String.format("%s (Level %s)", player.getName(), player.getExpLevel()))
                .author(user.getUsername(), "https://github.com/vitzlo/versus", user.getAvatarUrl()) // TODO: ut for more specific builder?
                .description("Player tag: " + player.getTag())
                .thumbnail(player.getLeague().getIconUrls().get("medium"))

                .addField("__Village Info__", Ut.fieldValuePairs(
                        "Town Hall Level", player.getTownHallLevel(),
                        "Town Hall Weapon Level", player.getTownHallWeaponLevel()
                ), false)
                .addField("__Multiplayer Info__", Ut.fieldValuePairs(
                        "Trophies", player.getTrophies(),
                        "Best Trophies", player.getBestTrophies(),
                        "Attack Wins", player.getAttackWins(),
                        "Defense Wins", player.getDefenseWins()
                ), false)
                .addField("__Clan Info__", Ut.fieldValuePairs(
                        "Clan Name", player.getClan().getName(),
                        "Clan Tag", player.getClan().getTag(),
                        "Donations Received", player.getDonationsReceived(),
                        "Donations Given", player.getDonations()
                ), false)
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
            throw new IOException(FileUt.DB_ERROR_MSG);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(String.format("User not found in the database. Use `%sclash help` to learn about registering.", Ut.PLACEHOLDER_PREFIX));
        }

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(String.format("https://api.clashofclans.com/v1/players/%s", ApiUt.encodeUrl(playerTag)))
                    .header("Authorization", "Bearer " + dataAccess.getClashToken())
                    .get().build();

            Response response = client.newCall(request).execute();
            response.close();

            Gson gson = new Gson();
            return gson.fromJson(response.body().string(), ClashPlayer.class); // TODO: ???
        } catch (NullPointerException | IOException e) { // TODO: ???
            throw new IOException(ApiUt.IO_ERROR_MSG);
        } catch (RuntimeException rte) {
            throw new RuntimeException(ApiUt.BAD_JSON_MSG);
        }
    }

    private boolean validatePlayer(String playerTag, String apiToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(String.format("{\"token\": \"%s\"}", apiToken), MediaType.parse("json"));
        Request request = new Request.Builder()
                .url(String.format("https://api.clashofclans.com/v1/players/%s/verifytoken", ApiUt.encodeUrl(playerTag)))
                .header("Authorization", "Bearer " + dataAccess.getClashToken())
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        response.close();
        return response.code() == 200;
    }
}
