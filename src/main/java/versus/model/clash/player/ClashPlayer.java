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
}
