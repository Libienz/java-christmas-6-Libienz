package christmas.domain.order;

import christmas.domain.menu.MenuCategory;

public class Order {
    private final OrderItems orderItems;
    private final OrderDate orderDate;

    private Order(OrderItems orderItems, OrderDate orderDate) {
        this.orderItems = orderItems;
        this.orderDate = orderDate;
    }

    public static Order of(OrderItems order, OrderDate orderDate) {
        return new Order(order, orderDate);
    }

    public boolean isOrderDateInPeriod(int startDay, int endDay) {
        return orderDate.isInPeriod(startDay, endDay);
    }

    public int calculateDateOffset(int day) {
        return orderDate.calculateDayOffset(day);
    }

    public int calculatePrice() {
        return orderItems.calculateTotalPrice();
    }

    public int countCategoryItem(MenuCategory menuCategory) {
        return orderItems.countCategoryItem(menuCategory);
    }

    public boolean isWeekendOrder() {
        return orderDate.isWeekend();
    }

    public int calculateDiscountedPrice(int benefitAmount) {
        return calculatePrice() - benefitAmount;
    }

    public boolean isOrderDateSame(int dayOfMonth) {
        return orderDate.isSameDate(dayOfMonth);
    }
}
