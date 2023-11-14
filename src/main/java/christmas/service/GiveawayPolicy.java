package christmas.service;

import christmas.domain.Order;
import christmas.domain.FreeGift;

public interface GiveawayPolicy {
    Boolean supports(Order order);

    FreeGift applyGiveaway(Order order);
}
