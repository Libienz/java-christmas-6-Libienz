package christmas.dto;

import christmas.domain.MenuItem;

public class GiveawayResultDto {
    private final MenuItem giveaway;
    private final Integer count;

    private GiveawayResultDto(MenuItem giveaway, Integer count) {
        this.giveaway = giveaway;
        this.count = count;
    }

    public static GiveawayResultDto of(MenuItem giveaway, Integer count) {
        return new GiveawayResultDto(giveaway, count);
    }

    public MenuItem getGiveaway() {
        return giveaway;
    }

    public Integer getCount() {
        return count;
    }
}
