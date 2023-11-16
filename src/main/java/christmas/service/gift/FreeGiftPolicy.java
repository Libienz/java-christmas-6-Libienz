package christmas.service.gift;

import christmas.domain.benefit.FreeGift;
import christmas.domain.order.Order;

public interface FreeGiftPolicy {
    Boolean supports(Order order);

    FreeGift applyFreeGift(Order order);
}
