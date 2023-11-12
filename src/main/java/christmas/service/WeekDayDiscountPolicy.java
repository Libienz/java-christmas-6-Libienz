package christmas.service;

import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.dto.DiscountResultDto;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private static final Integer EVENT_START_DAY = 1;
    private static final Integer EVENT_END_DAY = 31;
    private static final MenuCategory DISCOUNT_MENU_CATEGORY = MenuCategory.DESSERT;
    private static final String DISCOUNT_DESCRIPTION = "평일 할인";
    private static final Integer DISCOUNT_AMOUNT_PER_ITEM = 2023;

    @Override
    public Boolean supports(Order order) {
        return !order.isOrderDateWeekend();
    }

    @Override
    public DiscountResultDto applyDiscount(Order order) {
        return DiscountResultDto.of(DISCOUNT_DESCRIPTION, calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.countCategoryItem(DISCOUNT_MENU_CATEGORY) * DISCOUNT_AMOUNT_PER_ITEM;
    }
}
