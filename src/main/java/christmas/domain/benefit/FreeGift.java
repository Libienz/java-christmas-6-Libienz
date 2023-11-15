package christmas.domain.benefit;

import christmas.domain.menu.MenuItem;
import christmas.dto.benefit.FreeGiftDto;

public class FreeGift {
    private final MenuItem freeGift;
    private final Integer count;
    private final String description;

    public FreeGift(MenuItem freeGift, Integer count, String description) {
        this.freeGift = freeGift;
        this.count = count;
        this.description = description;
    }

    public static FreeGift of(MenuItem giveaway, Integer count, String description) {
        return new FreeGift(giveaway, count, description);
    }

    public String getDescription() {
        return description;
    }

    public Integer calculateFreeGiftPrice() {
        return count * freeGift.getPrice();
    }

    public FreeGiftDto toFreeGiftDto() {
        return FreeGiftDto.of(freeGift.getItemName(), count);
    }
}
