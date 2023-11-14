package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 내역 테스트")
class OrderTest {
    private Order testOrder;

    @BeforeEach
    void setUp() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        OrderItem orderItem3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItems orderItems = OrderItems.from(List.of(orderItem1, orderItem2, orderItem3));
        OrderDate orderDate = OrderDate.from(10);

        testOrder = Order.of(orderItems, orderDate);
    }

    @DisplayName("주문 날짜가 기간안에 속하는 경우를 알 수 있다")
    @Test
    void testOrderDateIsInPeriod() {
        assertThat(testOrder.isOrderDateInPeriod(1, 10)).isTrue();
    }

    @DisplayName("주문 날짜가 기간안에 속하지 않는 경우를 알 수 있다")
    @Test
    void testOrderDateIsNotInPeriod() {
        assertThat(testOrder.isOrderDateInPeriod(1, 9)).isFalse();
    }

    @DisplayName("주문 날짜와 특정 날짜의 일수 차이를 계산할 수 있다")
    void testCalculateDateOffset() {
        assertThat(testOrder.calculateDateOffset(20)).isEqualTo(10);
    }

    @DisplayName("총 주문 금액을 계산할 수 있다")
    @Test
    void testCalculateTotalPrice() {
        assertThat(testOrder.calculatePrice()).isEqualTo(315000);
    }

    @DisplayName("특정 카테고리 메뉴 주문 개수를 셀 수 있다")
    @Test
    void testCountCategoryOrderItem() {
        assertThat(testOrder.countCategoryItem(MenuCategory.MAIN)).isEqualTo(6);
    }

    @DisplayName("주말에 발생한 주문인지 알 수 있다")
    @Test
    void testIsWeekendOrder() {
        assertThat(testOrder.isWeekendOrder()).isFalse();
    }

    @DisplayName("특정 날짜에 발생한 주문인지 확인할 수 있다")
    @Test
    void testOrderDateSame() {
        assertThat(testOrder.isOrderDateSame(10)).isTrue();
    }
}