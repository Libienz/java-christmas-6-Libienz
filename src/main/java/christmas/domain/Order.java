package christmas.domain;

public class Order {
    private final OrderItems order;
    private final OrderDate orderDate;

    private Order(OrderItems order, OrderDate orderDate) {
        this.order = order;
        this.orderDate = orderDate;
    }

    public static Order of(OrderItems order, OrderDate orderDate) {
        return new Order(order, orderDate);
    }
}
