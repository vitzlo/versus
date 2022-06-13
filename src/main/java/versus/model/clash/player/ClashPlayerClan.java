package versus.model.clash.player;

import versus.model.clash.ClashEntity;

import java.util.Map;

public class ClashPlayerClan implements ClashEntity {
    private String tag;
    private int clanLevel;
    private String name;
    private Map<String, String> badgeUrls;

    public String getTag() {
        return tag;
    }

    public int getClanLevel() {
        return clanLevel;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getBadgeUrls() {
        return badgeUrls;
    }
}
