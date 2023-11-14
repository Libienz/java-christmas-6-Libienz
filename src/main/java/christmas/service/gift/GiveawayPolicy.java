package christmas.service.gift;

import christmas.domain.FreeGift;
import christmas.domain.Order;

public interface GiveawayPolicy {
    Boolean supports(Order order);

    FreeGift applyGiveaway(Order order);
}
