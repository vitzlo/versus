package versus.model.clash.player;

import versus.model.clash.ClashEntity;
import versus.model.clash.label.ClashLabel;
import versus.model.clash.league.ClashLeague;

import java.util.List;

public class ClashPlayer implements ClashEntity {
    private ClashPlayerClan clan;
    private ClashLeague league;
    private String role, warPreference;
    private int attackWins, townHallWeaponLevel, versusBattleWins;
    private ClashPlayerLegendStatistics legendStatistics;
    private List<ClashPlayerItemLevel> troops;
    private List<ClashPlayerItemLevel> heroes;
    private List<ClashPlayerItemLevel> spells;
    private int defenseWins, townHallLevel;
    private List<ClashLabel> labels;
    private String tag, name;
    private int expLevel, trophies, bestTrophies, donations, donationsReceived, builderHallLevel,
            versusTrophies, bestVersusTrophies, warStars;
    private List<ClashPlayerAchievementProgress> achievements;
    private int versusBattleWinCount;

    public ClashPlayerClan getClan() {
        return clan;
    }

    public ClashLeague getLeague() {
        return league;
    }

    public String getRole() {
        return role;
    }

    public String getWarPreference() {
        return warPreference;
    }

    public int getAttackWins() {
        return attackWins;
    }

    public int getTownHallWeaponLevel() {
        return townHallWeaponLevel;
    }

    public int getVersusBattleWins() {
        return versusBattleWins;
    }

    public ClashPlayerLegendStatistics getLegendStatistics() {
        return legendStatistics;
    }

    public List<ClashPlayerItemLevel> getTroops() {
        return troops;
    }

    public List<ClashPlayerItemLevel> getHeroes() {
        return heroes;
    }

    public List<ClashPlayerItemLevel> getSpells() {
        return spells;
    }

    public int getDefenseWins() {
        return defenseWins;
    }

    public int getTownHallLevel() {
        return townHallLevel;
    }

    public List<ClashLabel> getLabels() {
        return labels;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public int getExpLevel() {
        return expLevel;
    }

    public int getTrophies() {
        return trophies;
    }

    public int getBestTrophies() {
        return bestTrophies;
    }

    public int getDonations() {
        return donations;
    }

    public int getDonationsReceived() {
        return donationsReceived;
    }

    public int getBuilderHallLevel() {
        return builderHallLevel;
    }

    public int getVersusTrophies() {
        return versusTrophies;
    }

    public int getBestVersusTrophies() {
        return bestVersusTrophies;
    }

    public int getWarStars() {
        return warStars;
    }

    public List<ClashPlayerAchievementProgress> getAchievements() {
        return achievements;
    }

    public int getVersusBattleWinCount() {
        return versusBattleWinCount;
    }
}
