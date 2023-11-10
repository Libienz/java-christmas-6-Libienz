package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("주문 내역 테스트")
class OrderTest {
    @DisplayName("주문 개수의 범위를 벗어나는 입력은 생성 시 검증에 실패한다")
    @ParameterizedTest()
    @ValueSource(ints = {0, 21, 22})
    void testInvalidRangeOrderCountOrder(int orderCount) {
        EnumMap<MenuItem, Integer> order = new EnumMap<>(MenuItem.class);
        order.put(MenuItem.BARBECUE_RIBS, orderCount);
        OrderDate orderDate = OrderDate.from(1);

        assertThatThrownBy(() -> Order.of(order, orderDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 입력은 생성 시 검증을 통과한다")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 20})
    void testValidRangeOrderCountOrder(int orderCount) {
        EnumMap<MenuItem, Integer> order = new EnumMap<>(MenuItem.class);
        order.put(MenuItem.BARBECUE_RIBS, orderCount);
        OrderDate orderDate = OrderDate.from(1);

        assertThatNoException().isThrownBy(() -> Order.of(order, orderDate));
    }
}