package versus.model.clash.player;

import versus.model.clash.ClashEntity;

public class ClashPlayerAchievementProgress implements ClashEntity {
    private int stars, value;
    private String name;
    private int target;
    private String info, completionInfo, village;

    public int getStars() {
        return stars;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getTarget() {
        return target;
    }

    public String getInfo() {
        return info;
    }

    public String getCompletionInfo() {
        return completionInfo;
    }

    public String getVillage() {
        return village;
    }
}
