package christmas.service.discount;

import christmas.domain.benefit.DiscountDetail;
import christmas.domain.order.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final Integer EVENT_START_DAY = 1;
    private static final Integer EVENT_END_DAY = 25;
    private static final String DISCOUNT_DESCRIPTION = "크리스마스 디데이 할인";
    private static final Integer EVENT_APPLY_THRESHOLD_PRICE = 10000;
    private static final Integer DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final Integer DISCOUNT_INCREASE_PER_DAY = 100;

    @Override
    public Boolean supports(Order order) {
        if (isInsufficientOrderAmountForEvent(order)) {
            return false;
        }
        if (isOutOfEventPeriodOrder(order)) {
            return false;
        }
        return true;
    }

    @Override
    public DiscountDetail applyDiscount(Order order) {
        return DiscountDetail.of(DISCOUNT_DESCRIPTION, calculateDiscountAmount(order));
    }

    private boolean isInsufficientOrderAmountForEvent(Order order) {
        return order.calculatePrice() < EVENT_APPLY_THRESHOLD_PRICE;
    }

    private Integer calculateDiscountAmount(Order order) {
        return DEFAULT_DISCOUNT_AMOUNT + order.calculateOrderDateOffset(EVENT_START_DAY) * DISCOUNT_INCREASE_PER_DAY;
    }

    private boolean isOutOfEventPeriodOrder(Order order) {
        return !order.isOrderDateInPeriod(EVENT_START_DAY, EVENT_END_DAY);
    }
}
