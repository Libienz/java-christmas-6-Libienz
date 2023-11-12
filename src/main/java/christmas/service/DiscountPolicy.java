package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountResultDto;

public interface DiscountPolicy {
    Boolean supports(Order order);

    DiscountResultDto applyDiscount(Order order);
}
