package christmas.domain;

import christmas.dto.FreeGiftDto;

public class FreeGift {
    private final MenuItem giveaway;
    private final Integer count;
    private final String description;

    public FreeGift(MenuItem giveaway, Integer count, String description) {
        this.giveaway = giveaway;
        this.count = count;
        this.description = description;
    }

    public static FreeGift of(MenuItem giveaway, Integer count, String description) {
        return new FreeGift(giveaway, count, description);
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

    public FreeGiftDto toFreeGiftDto() {
        return FreeGiftDto.of(giveaway.getItemName(), count);
    }
}
