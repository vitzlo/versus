package versus.model.clash.player;

import versus.model.clash.ClashEntity;
import versus.model.clash.league.ClashLegendLeagueSeasonResult;

public class ClashPlayerLegendStatistics implements ClashEntity {
    private ClashLegendLeagueSeasonResult currentSeason, bestSeason, previousSeason, previousVersusSeason, bestVersusSeason;
    private int legendTrophies;

    public ClashLegendLeagueSeasonResult getCurrentSeason() {
        return currentSeason;
    }

    public ClashLegendLeagueSeasonResult getBestSeason() {
        return bestSeason;
    }

    public ClashLegendLeagueSeasonResult getPreviousSeason() {
        return previousSeason;
    }

    public ClashLegendLeagueSeasonResult getPreviousVersusSeason() {
        return previousVersusSeason;
    }

    public ClashLegendLeagueSeasonResult getBestVersusSeason() {
        return bestVersusSeason;
    }

    public int getLegendTrophies() {
        return legendTrophies;
    }
}
