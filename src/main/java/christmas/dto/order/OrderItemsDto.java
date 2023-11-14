package christmas.dto.order;

import java.util.List;

public class OrderItemsDto {
    private final List<OrderItemDto> orderItemDtos;

    private OrderItemsDto(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

    public static OrderItemsDto from(List<OrderItemDto> orderItemDtos) {
        return new OrderItemsDto(orderItemDtos);
    }

    public List<OrderItemDto> getOrderItemDtos() {
        return orderItemDtos;
    }
}
