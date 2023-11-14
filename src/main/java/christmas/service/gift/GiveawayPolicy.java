package christmas.service.gift;

import christmas.domain.benefit.FreeGift;
import christmas.domain.order.Order;

public interface GiveawayPolicy {
    Boolean supports(Order order);

    FreeGift applyGiveaway(Order order);
}
