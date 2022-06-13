package versus.model.clash.player;

import versus.model.clash.ClashEntity;

public class ClashPlayerItemLevel implements ClashEntity {
    private int level;
    private String name;
    private int maxLevel;
    private String village;
    private boolean superTroopIsActive;

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getVillage() {
        return village;
    }

    public boolean isSuperTroopIsActive() {
        return superTroopIsActive;
    }
}
