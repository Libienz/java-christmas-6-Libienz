package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountResultDto;
import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final String DISCOUNT_DESCRIPTION = "특별 할인";
    private static final Integer DISCOUNT_AMOUNT = 1000;
    private static final List<Integer> STARRED_DAYS = List.of(3, 10, 17, 24, 25, 31);

    @Override
    public Boolean supports(Order order) {
        if (order.calculatePrice() < 10000) {
            return false;
        }
        return STARRED_DAYS.stream().anyMatch(order::isOrderDateSame);
    }

    @Override
    public DiscountResultDto applyDiscount(Order order) {
        return DiscountResultDto.of(DISCOUNT_DESCRIPTION, DISCOUNT_AMOUNT);
    }
}
