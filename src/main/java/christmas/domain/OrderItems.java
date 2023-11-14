package christmas.domain;

import christmas.dto.OrderItemsDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, Integer> orderItemsMap = orderItems.stream()
                .collect(Collectors.toMap(OrderItem::getMenuName, OrderItem::getOrderCount));
        return OrderItemsDto.from(orderItemsMap);
    }

    private void validate(List<OrderItem> orderItems) {
        validateDuplicateOrderItem(orderItems);
    }

    private void validateDuplicateOrderItem(List<OrderItem> orderItems) {
        if (hasDuplicateItem(orderItems)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private void validateBeverageOnly(List<OrderItem> orderItems) {
        if (!orderItems.stream().allMatch(OrderItem::isBeverageOrder)) {
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
