package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import christmas.dto.order.OrderItemDto;
import christmas.exception.orders.OrderCountRangeException;

public class OrderItem {
    private static final Integer MIN_ORDER_COUNT = 1;

    private final MenuItem menuItem;
    private final Integer orderCount;

    private OrderItem(MenuItem menuItem, Integer orderCount) {
        validate(menuItem, orderCount);
        this.menuItem = menuItem;
        this.orderCount = orderCount;
    }

    public static OrderItem of(MenuItem menuItem, Integer orderCount) {
        return new OrderItem(menuItem, orderCount);
    }

    public int countCategoryItem(MenuCategory menuCategory) {
        if (menuItem.getCategory() == menuCategory) {
            return orderCount;
        }
        return 0;
    }

    public OrderItemDto toOrderItemDto() {
        return OrderItemDto.of(menuItem.getItemName(), orderCount);
    }

    public String getMenuName() {
        return menuItem.getItemName();
    }

    public boolean isBeverageOrder() {
        return menuItem.getCategory() == MenuCategory.BEVERAGE;
    }

    public int getPrice() {
        return menuItem.getPrice() * orderCount;
    }

    private void validate(MenuItem menuItem, Integer orderCount) {
        validateOrderCount(orderCount);
    }

    private void validateOrderCount(Integer orderCount) {
        if (orderCount < MIN_ORDER_COUNT) {
            throw new OrderCountRangeException();
        }
    }
}
