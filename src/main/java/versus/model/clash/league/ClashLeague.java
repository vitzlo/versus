package versus.model.clash.league;

import versus.model.clash.ClashEntity;

import java.util.Map;

public class ClashLeague implements ClashEntity {
    private String name;
    private int id;
    private Map<String, String> iconUrls;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getIconUrls() {
        return iconUrls;
    }
}
