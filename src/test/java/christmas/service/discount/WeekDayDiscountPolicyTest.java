package christmas.service.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.DiscountDetail;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("평일 할인 정책 테스트")
class WeekDayDiscountPolicyTest {
    private WeekDayDiscountPolicy weekDayDiscountPolicy;

    @BeforeEach
    void setUp() {
        weekDayDiscountPolicy = new WeekDayDiscountPolicy();
    }

    @DisplayName("평일 할인 적용 금액을 올바르게 판별할 수 있다")
    @Test
    void testCalculateDiscountAmount() {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        DiscountDetail discountDetail = weekDayDiscountPolicy.calculateApplicableDiscount(order);

        assertThat(discountDetail.getDiscountAmount()).isEqualTo(2023 * 3);
    }

    @DisplayName("일 ,월, 화, 수, 목에는 평일 할인이 적용된다")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void testSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(weekDayDiscountPolicy.supports(order)).isTrue();
    }

    @DisplayName("총 주문 금액이 10000원을 넘지 않으면 이벤트 적용이 되지 않는다")
    @Test
    void testEventApplyThresholdMoney() {
        OrderItem item1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 1);
        List<OrderItem> orderItems = List.of(item1);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(weekDayDiscountPolicy.supports(order)).isFalse();
    }

    @DisplayName("금요일 토요일에는 평일 할인이 적용되지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void testNonSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(weekDayDiscountPolicy.supports(order)).isFalse();
    }

    @DisplayName("디저트 메뉴를 주문하지 않았다면 평일 할인이 적용되지 않는다")
    @Test
    void testNoSupportsForNoDessertMenuOrder() {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(3));

        assertThat(weekDayDiscountPolicy.supports(order)).isFalse();
    }
}