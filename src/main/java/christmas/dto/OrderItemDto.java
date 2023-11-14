package christmas.dto;

public class OrderItemDto {
    private final String menuName;
    private final Integer orderCount;

    private OrderItemDto(String menuName, Integer orderCount) {
        this.menuName = menuName;
        this.orderCount = orderCount;
    }

    public static OrderItemDto of(String menuName, Integer orderCount) {
        return new OrderItemDto(menuName, orderCount);
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getOrderCount() {
        return orderCount;
    }
}
