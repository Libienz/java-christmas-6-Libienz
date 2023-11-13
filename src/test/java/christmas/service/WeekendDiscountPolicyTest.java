package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItem;
import christmas.domain.OrderItems;
import christmas.dto.DiscountResultDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("주말 할인 정책 테스트")
class WeekendDiscountPolicyTest {
    private WeekendDiscountPolicy weekendDiscountPolicy;

    @BeforeEach
    void setUp() {
        weekendDiscountPolicy = new WeekendDiscountPolicy();
    }

    @DisplayName("주말 할인 적용 금액을 올바르게 판별할 수 있다")
    @Test
    void testCalculateDiscountAmount() {
        OrderItem item1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(3));
        DiscountResultDto discountResultDto = weekendDiscountPolicy.applyDiscount(order);

        assertThat(discountResultDto.getDiscountAmount()).isEqualTo(2023 * 3);
    }

    @DisplayName("일 ,월, 화, 수, 목에는 주말 할인이 적용되지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void testSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(weekendDiscountPolicy.supports(order)).isFalse();
    }

    @DisplayName("금요일 토요일에는 평일 할인이 적용되지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void testNonSupportableOrder(int day) {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(day));

        assertThat(weekendDiscountPolicy.supports(order)).isTrue();
    }
}