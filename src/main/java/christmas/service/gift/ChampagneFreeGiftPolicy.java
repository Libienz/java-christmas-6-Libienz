package christmas.service.gift;

import christmas.domain.benefit.FreeGift;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.Order;

public class ChampagneFreeGiftPolicy implements FreeGiftPolicy {
    private static final MenuItem FREE_GIFT_ITEM = MenuItem.CHAMPAGNE;
    private static final Integer FREE_GIFT_APPLY_THRESHOLD_PRICE = 120000;
    private static final Integer FREE_GIFT_COUNT = 1;
    private static final String DESCRIPTION = "증정 이벤트";

    @Override
    public Boolean supports(Order order) {
        if (insufficientOrderAmountForGiveaway(order)) {
            return false;
        }
        return true;
    }

    @Override
    public FreeGift applyFreeGift(Order order) {
        return FreeGift.of(FREE_GIFT_ITEM, FREE_GIFT_COUNT, DESCRIPTION);
    }

    private boolean insufficientOrderAmountForGiveaway(Order order) {
        return order.calculatePrice() < FREE_GIFT_APPLY_THRESHOLD_PRICE;
    }
}
