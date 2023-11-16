package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.dto.order.OrderItemsDto;
import christmas.exception.orders.BeverageOnlyOrderException;
import christmas.exception.orders.DuplicateOrderItemException;
import christmas.exception.orders.OrderCountRangeException;
import java.util.List;

public class OrderItems {
    private static final Integer MAX_ORDER_COUNT = 20;

    private final List<OrderItem> orderItems;

    private OrderItems(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = orderItems;
    }

    public static OrderItems from(List<OrderItem> orderItems) {
        return new OrderItems(orderItems);
    }

    public Integer countCategoryItem(MenuCategory menuCategory) {
        return orderItems.stream()
                .map(orderItem -> orderItem.countCategoryItem(menuCategory))
                .reduce(0, Integer::sum);
    }

    public Integer calculateTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::calculatePrice)
                .sum();
    }

    public OrderItemsDto toOrderItemsDto() {
        return OrderItemsDto.from(orderItems.stream()
                .map(OrderItem::toOrderItemDto)
                .toList());
    }

    private void validate(List<OrderItem> orderItems) {
        validateDuplicateOrderItem(orderItems);
        validateBeverageOnly(orderItems);
        validateOrderCount(orderItems);
    }

    private void validateDuplicateOrderItem(List<OrderItem> orderItems) {
        if (hasDuplicateItem(orderItems)) {
            throw new DuplicateOrderItemException();
        }
    }

    private void validateOrderCount(List<OrderItem> orderItems) {
        if (isOrderCountExceedMaxCount(orderItems)) {
            throw new OrderCountRangeException();
        }
    }

    private void validateBeverageOnly(List<OrderItem> orderItems) {
        if (orderItems.stream().allMatch(OrderItem::isBeverageOrder)) {
            throw new BeverageOnlyOrderException();
        }
    }

    private boolean hasDuplicateItem(List<OrderItem> orderItems) {
        return orderItems.size() != orderItems.stream()
                .map(OrderItem::getMenuName)
                .distinct()
                .count();
    }

    private boolean isOrderCountExceedMaxCount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.toOrderItemDto().getOrderCount())
                .sum() > MAX_ORDER_COUNT;
    }
}
