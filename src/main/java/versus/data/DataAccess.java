package versus.data;

import discord4j.common.util.Snowflake;
import versus.FileUt;
import versus.Ut;

import java.io.File;
import java.io.IOException;

public class DataAccess {
    private final File clashPlayerTagCsv = new File("src/main/java/versus/data/clashtags.csv");

    public String getGuildPrefix(Snowflake guildId) {
        return Ut.DEFAULT_PREFIX; // TODO: implement
    }

    public String getClashPlayerTag(String userId) throws IOException, IllegalArgumentException {
        return FileUt.csvValueAtKeyCell(clashPlayerTagCsv, "USER_ID", userId, "PLAYER_TAG");
    }
}
