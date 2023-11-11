package christmas.domain;

import java.util.EnumMap;

public class OrderItems {
    private static final Integer MAX_ORDER_COUNT = 20;
    private static final Integer MIN_ORDER_COUNT = 1;
    private static final String INVALID_RANGE_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private final EnumMap<MenuItem, Integer> orderItems;

    private OrderItems(EnumMap<MenuItem, Integer> orderItems) {
        validate(orderItems);
        this.orderItems = orderItems;
    }

    public static OrderItems from(EnumMap<MenuItem, Integer> orderItems) {
        return new OrderItems(orderItems);
    }

    private void validate(EnumMap<MenuItem, Integer> order) {
        validateOrderCount(order);
    }

    private void validateOrderCount(EnumMap<MenuItem, Integer> order) {
        if (isInvalidOrderCount(order)) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    private boolean isInvalidOrderCount(EnumMap<MenuItem, Integer> order) {
        return order.values().stream()
                .anyMatch(count -> count < MIN_ORDER_COUNT || count > MAX_ORDER_COUNT);
    }

}
