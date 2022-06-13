package versus.data;

import versus.FileUt;
import versus.Ut;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DataAccess {
    private final File _tokensCsv = new File("src/main/java/versus/data/_tokens.csv");
    private final File clashPlayerTagCsv = new File("src/main/java/versus/data/clashtags.csv");
    private final File prefixesCsv = new File("src/main/java/versus/data/prefixes.csv");

    public String getGuildPrefix(String guildId) throws IOException, IllegalArgumentException {
        return FileUt.csvValueAtKeyCell(prefixesCsv, "GUILD_ID", guildId, "PREFIX");
    }

    public String getGuildPrefixElseDefault(String guildId) {
        try {
            return getGuildPrefix(guildId);
        } catch (IOException | IllegalArgumentException e) {
            return Ut.DEFAULT_PREFIX;
        }
    }

    public String getClashToken() throws IOException, IllegalArgumentException {
        return FileUt.csvValueAtKeyCell(_tokensCsv, "NAME", "clash", "TOKEN");
    }

    public String getClashPlayerTag(String userId) throws IOException, IllegalArgumentException {
        return FileUt.csvValueAtKeyCell(clashPlayerTagCsv, "USER_ID", userId, "PLAYER_TAG");
    }

    public boolean hasClashUserId(String userId) throws IOException, IllegalArgumentException {
        return FileUt.csvContainsValue(clashPlayerTagCsv, "USER_ID", userId);
    }

    // TODO: include discord username for readability
    public void setClashPlayerTag(String userId, String playerTag) throws IOException {
        if (hasClashUserId(userId)) {
            FileUt.csvOverwriteLine(clashPlayerTagCsv, "USER_ID", userId, Map.of("USER_ID", userId, "PLAYER_TAG", playerTag));
        } else {
            FileUt.csvWriteLine(clashPlayerTagCsv, Map.of("USER_ID", userId, "PLAYER_TAG", playerTag));
        }
    }
}
