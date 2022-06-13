package versus.data;

import versus.FileUt;
import versus.Ut;

import java.io.File;
import java.io.IOException;

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
}
