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

@DisplayName("특별 할인 정책 테스트")
class SpecialDiscountPolicyTest {
    private SpecialDiscountPolicy specialDiscountPolicy;

    @BeforeEach
    void setUp() {
        specialDiscountPolicy = new SpecialDiscountPolicy();
    }

    @DisplayName("특별 할인 적용 금액을 올바르게 판별할 수 있다")
    @Test
    void testCalculateDiscountAmount() {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(3));
        DiscountDetail discountDetail = specialDiscountPolicy.calculateApplicableDiscount(order);

        assertThat(discountDetail.getDiscountAmount()).isEqualTo(1000);
    }

    @DisplayName("총 주문 금액이 10000원을 넘지 않으면 이벤트 적용이 되지 않는다")
    @Test
    void testEventApplyThresholdMoney() {
        OrderItem item1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 1);
        List<OrderItem> orderItems = List.of(item1);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(specialDiscountPolicy.supports(order)).isFalse();
    }

    @DisplayName("3일 ,10일, 17일, 24일, 25일, 31일에는 특별 할인이 적용된다")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void testSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(specialDiscountPolicy.supports(order)).isTrue();
    }

    @DisplayName("3일 ,10일, 17일, 24일, 25일, 31일이 아닌 날에는 특별 할인이 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9, 12, 13, 27, 28})
    void testNonSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(specialDiscountPolicy.supports(order)).isFalse();
    }
}