package christmas.dto;

import christmas.domain.MenuItem;

public class GiveawayResultDto {
    private final MenuItem giveaway;
    private final Integer count;
    private final String description;

    public GiveawayResultDto(MenuItem giveaway, Integer count, String description) {
        this.giveaway = giveaway;
        this.count = count;
        this.description = description;
    }

    public static GiveawayResultDto of(MenuItem giveaway, Integer count, String description) {
        return new GiveawayResultDto(giveaway, count, description);
    }

    public String getDescription() {
        return description;
    }

    public Integer getDiscountAmount() {
        return count * giveaway.getPrice();
    }

    public MenuItem getGiveaway() {
        return giveaway;
    }

    public Integer getCount() {
        return count;
    }
}
