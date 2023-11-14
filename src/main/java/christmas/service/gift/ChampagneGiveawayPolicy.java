package christmas.service.gift;

import christmas.domain.FreeGift;
import christmas.domain.MenuItem;
import christmas.domain.Order;

public class ChampagneGiveawayPolicy implements GiveawayPolicy {
    private static final MenuItem GIVEAWAY_MENU_ITEM = MenuItem.CHAMPAGNE;
    private static final Integer GIVEAWAY_APPLY_THRESHOLD_PRICE = 120000;
    private static final Integer EVENT_APPLY_THRESHOLD_PRICE = 10000;
    private static final Integer GIVEAWAY_COUNT = 1;
    private static final String DESCRIPTION = "증정 이벤트";

    @Override
    public Boolean supports(Order order) {
        if (insufficientOrderAmountForEvent(order)) {
            return false;
        }
        if (insufficientOrderAmountForGiveaway(order)) {
            return false;
        }
        return true;
    }

    @Override
    public FreeGift applyGiveaway(Order order) {
        return FreeGift.of(GIVEAWAY_MENU_ITEM, GIVEAWAY_COUNT, DESCRIPTION);
    }

    private boolean insufficientOrderAmountForGiveaway(Order order) {
        return order.calculatePrice() < GIVEAWAY_APPLY_THRESHOLD_PRICE;
    }

    private boolean insufficientOrderAmountForEvent(Order order) {
        return order.calculatePrice() < EVENT_APPLY_THRESHOLD_PRICE;
    }
}
