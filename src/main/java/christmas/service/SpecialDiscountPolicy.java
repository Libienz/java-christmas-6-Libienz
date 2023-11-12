package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountResultDto;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final String DISCOUNT_DESCRIPTION = "특별 할인";
    private static final Integer DISCOUNT_AMOUNT = 1000;
    private static final Set<Integer> STARRED_DAYS = new HashSet<>(Arrays.asList(3, 10, 17, 24, 25, 31));

    @Override
    public Boolean supports(Order order) {
        return STARRED_DAYS.stream().anyMatch(order::isOrderDateSame);
    }

    @Override
    public DiscountResultDto applyDiscount(Order order) {
        return DiscountResultDto.of(DISCOUNT_DESCRIPTION, DISCOUNT_AMOUNT);
    }
}
