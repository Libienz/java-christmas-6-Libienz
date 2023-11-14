package christmas.service;

import christmas.domain.Order;
import christmas.dto.FreeGift;

public interface GiveawayPolicy {
    Boolean supports(Order order);

    FreeGift applyGiveaway(Order order);
}
