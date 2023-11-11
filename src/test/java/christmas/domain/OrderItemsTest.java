package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 목록 테스트")
class OrderItemsTest {
    @DisplayName("중복이 있는 경우 생성 시 검증에 실패한다")
    @Test
    void testDuplicateOrderItems() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatThrownBy(() -> OrderItems.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복이 없는 경우 생성 시 검증을 통과한다")
    @Test
    void testNonDuplicateOrderItems() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatNoException().isThrownBy(() -> OrderItems.from(orderItems));
    }
}