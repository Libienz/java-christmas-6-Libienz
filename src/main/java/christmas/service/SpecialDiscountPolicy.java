package christmas.service;

import christmas.domain.Order;
import christmas.domain.DiscountDetails;
import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final String DISCOUNT_DESCRIPTION = "특별 할인";
    private static final Integer DISCOUNT_AMOUNT = 1000;
    private static final Integer EVENT_APPLY_THRESHOLD_PRICE = 10000;
    private static final List<Integer> STARRED_DAYS = List.of(3, 10, 17, 24, 25, 31);

    @Override
    public Boolean supports(Order order) {
        if (isInsufficientOrderAmountForEvent(order)) {
            return false;
        }
        if (isNotStarredDateOrder(order)) {
            return false;
        }
        return true;
    }

    @Override
    public DiscountDetails applyDiscount(Order order) {
        return DiscountDetails.of(DISCOUNT_DESCRIPTION, DISCOUNT_AMOUNT);
    }

    private boolean isInsufficientOrderAmountForEvent(Order order) {
        return order.calculatePrice() < EVENT_APPLY_THRESHOLD_PRICE;
    }

    private boolean isNotStarredDateOrder(Order order) {
        return STARRED_DAYS.stream().noneMatch(order::isOrderDateSame);
    }
}
