package versus.model.clash.player;

import versus.model.clash.ClashEntity;
import versus.model.clash.label.ClashLabel;
import versus.model.clash.league.ClashLeague;

import java.util.List;

public class ClashPlayer implements ClashEntity {
    public ClashPlayerClan clan;
    public ClashLeague league;
    public String role, warPreference;
    public int attackWins, townHallWeaponLevel, versusBattleWins;
    public ClashPlayerLegendStatistics legendStatistics;
    public List<ClashPlayerItemLevel> troops;
    public List<ClashPlayerItemLevel> heroes;
    public List<ClashPlayerItemLevel> spells;
    public int defenseWins, townHallLevel;
    public List<ClashLabel> labels;
    public String tag, name;
    public int expLevel, trophies, bestTrophies, donations, donationsReceived, builderHallLevel,
            versusTrophies, bestVersusTrophies, warStars;
    public List<ClashPlayerAchievementProgress> achievements;
    public int versusBattleWinCount;
}
