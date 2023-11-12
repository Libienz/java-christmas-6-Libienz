package christmas.service;

import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.dto.DiscountResultDto;

public class WeekendDiscountPolicy implements DiscountPolicy {
    private static final MenuCategory DISCOUNT_MENU_CATEGORY = MenuCategory.MAIN;
    private static final String DISCOUNT_DESCRIPTION = "주말 할인";
    private static final Integer DISCOUNT_AMOUNT_PER_ITEM = 2023;

    @Override
    public Boolean supports(Order order) {
        return order.isOrderDateWeekend();
    }

    @Override
    public DiscountResultDto applyDiscount(Order order) {
        return DiscountResultDto.of(DISCOUNT_DESCRIPTION, calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.countCategoryItem(DISCOUNT_MENU_CATEGORY) * DISCOUNT_AMOUNT_PER_ITEM;
    }

}
