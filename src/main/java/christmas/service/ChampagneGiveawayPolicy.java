package christmas.service;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import christmas.dto.GiveawayResultDto;

public class ChampagneGiveawayPolicy implements GiveawayPolicy {
    private static final MenuItem GIVEAWAY_MENU_ITEM = MenuItem.CHAMPAGNE;
    private static final Integer THRESHOLD_AMOUNT = 120000;
    private static final Integer GIVEAWAY_COUNT = 1;
    private static final String DESCRIPTION = "증정 이벤트";

    @Override
    public Boolean supports(Order order) {
        return order.calculatePrice() >= THRESHOLD_AMOUNT;
    }

    @Override
    public GiveawayResultDto applyGiveaway(Order order) {
        return GiveawayResultDto.of(GIVEAWAY_MENU_ITEM, GIVEAWAY_COUNT, DESCRIPTION);
    }
}
