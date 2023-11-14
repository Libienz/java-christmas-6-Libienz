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

@DisplayName("크리스마스 D-DAY 할인 정책 테스트")
class ChristmasDiscountPolicyTest {
    private ChristmasDiscountPolicy christmasDiscountPolicy;

    @BeforeEach
    void setUp() {
        christmasDiscountPolicy = new ChristmasDiscountPolicy();
    }

    @DisplayName("날짜에 맞춰 할인 금액을 올바르게 계산할 수 있다")
    @Test
    void testCalculateDiscountAmount() {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(3));
        DiscountDetail discountDetail = christmasDiscountPolicy.calculateApplicableDiscount(order);

        assertThat(discountDetail.getDiscountAmount()).isEqualTo(1200);
    }

    @DisplayName("크리스마스 할인이 적용 가능한지 날짜로 판별할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 24, 25})
    void testSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(christmasDiscountPolicy.supports(order)).isTrue();
    }

    @DisplayName("총 주문 금액이 10000원을 넘지 않으면 이벤트 적용이 되지 않는다")
    @Test
    void testEventApplyThresholdMoney() {
        OrderItem item1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 1);
        List<OrderItem> orderItems = List.of(item1);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(christmasDiscountPolicy.supports(order)).isFalse();
    }

    @DisplayName("할인이 지원되지 않는 경우 supports가 false를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 31})
    void testNonSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(christmasDiscountPolicy.supports(order)).isFalse();
    }
}