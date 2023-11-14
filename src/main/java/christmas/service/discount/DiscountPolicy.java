package christmas.service.discount;

import christmas.domain.DiscountDetail;
import christmas.domain.Order;

public interface DiscountPolicy {
    Boolean supports(Order order);

    DiscountDetail applyDiscount(Order order);
}
