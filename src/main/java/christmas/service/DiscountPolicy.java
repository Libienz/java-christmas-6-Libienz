package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountDetails;

public interface DiscountPolicy {
    Boolean supports(Order order);

    DiscountDetails applyDiscount(Order order);
}
