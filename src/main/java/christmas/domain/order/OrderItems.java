package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.dto.OrderItemsDto;
import java.util.List;

public class OrderItems {
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
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
                .mapToInt(OrderItem::getPrice)
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
    }

    private void validateDuplicateOrderItem(List<OrderItem> orderItems) {
        if (hasDuplicateItem(orderItems)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private void validateBeverageOnly(List<OrderItem> orderItems) {
        if (orderItems.stream().allMatch(OrderItem::isBeverageOrder)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private boolean hasDuplicateItem(List<OrderItem> orderItems) {
        return orderItems.size() != orderItems.stream()
                .map(OrderItem::getMenuName)
                .distinct()
                .count();
    }
}