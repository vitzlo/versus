package versus.model.clash.player;

import versus.model.clash.ClashEntity;
import versus.model.clash.league.ClashLegendLeagueSeasonResult;

public class ClashPlayerLegendStatistics implements ClashEntity {
    private ClashLegendLeagueSeasonResult currentSeason, bestSeason, previousSeason, previousVersusSeason, bestVersusSeason;
    private int legendTrophies;
}
