package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountResultDto;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final Integer EVENT_START_DAY = 1;
    private static final Integer EVENT_END_DAY = 26;
    private static final String DISCOUNT_DESCRIPTION = "크리스마스 디데이 할인";
    private static final Integer DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final Integer DISCOUNT_INCREASE_PER_DAY = 100;


    @Override
    public Boolean supports(Order order) {
        return order.isOrderDateInPeriod(EVENT_START_DAY, EVENT_END_DAY);
    }

    @Override
    public DiscountResultDto applyDiscount(Order order) {
        return DiscountResultDto.of(DISCOUNT_DESCRIPTION, calculateDiscountAmount(order));
    }

    private Integer calculateDiscountAmount(Order order) {
        return DEFAULT_DISCOUNT_AMOUNT + order.calculateOrderDateOffset(EVENT_START_DAY) * DISCOUNT_INCREASE_PER_DAY;
    }
}
