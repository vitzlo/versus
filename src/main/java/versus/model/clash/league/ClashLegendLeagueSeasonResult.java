package versus.model.clash.league;

import versus.model.clash.ClashEntity;

public class ClashLegendLeagueSeasonResult implements ClashEntity {
    private int trophies;
    private String id;
    private int rank;

    public int getTrophies() {
        return trophies;
    }

    public String getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }
}
