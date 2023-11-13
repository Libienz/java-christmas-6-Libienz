package christmas.dto;

import java.util.Map;

public class OrderItemsDto {
    private final Map<String, Integer> orderItems;

    private OrderItemsDto(Map<String, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderItemsDto from(Map<String, Integer> orderItems) {
        return new OrderItemsDto(orderItems);
    }

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }
}
