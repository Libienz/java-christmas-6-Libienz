package christmas.service.discount;

import christmas.domain.benefit.DiscountDetail;
import christmas.domain.order.Order;

public interface DiscountPolicy {
    Boolean supports(Order order);

    DiscountDetail calculateApplicableDiscount(Order order);
}
