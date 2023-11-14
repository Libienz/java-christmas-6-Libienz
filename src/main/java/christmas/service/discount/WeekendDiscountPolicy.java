package christmas.service.discount;

import christmas.domain.benefit.DiscountDetail;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;

public class WeekendDiscountPolicy implements DiscountPolicy {
    private static final MenuCategory DISCOUNT_MENU_CATEGORY = MenuCategory.MAIN;
    private static final String DISCOUNT_DESCRIPTION = "주말 할인";
    private static final Integer EVENT_APPLY_THRESHOLD_PRICE = 10000;
    private static final Integer DISCOUNT_AMOUNT_PER_ITEM = 2023;

    @Override
    public Boolean supports(Order order) {
        if (isInsufficientOrderAmountForEvent(order)) {
            return false;
        }
        if (isNotWeekendOrder(order)) {
            return false;
        }
        if (isNoOrderForDiscountedCategory(order)) {
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

    private boolean isNotWeekendOrder(Order order) {
        return !order.isOrderDateWeekend();
    }

    private boolean isNoOrderForDiscountedCategory(Order order) {
        return order.countCategoryItem(DISCOUNT_MENU_CATEGORY) == 0;
    }

    private int calculateDiscountAmount(Order order) {
        return order.countCategoryItem(DISCOUNT_MENU_CATEGORY) * DISCOUNT_AMOUNT_PER_ITEM;
    }
}
