package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("주문 아이템(메뉴와 개수) 테스트")
class OrderItemTest {
    @DisplayName("주문 개수의 범위를 벗어나는 입력은 생성 시 검증에 실패한다")
    @ParameterizedTest()
    @ValueSource(ints = {0, 21, 22})
    void testInvalidRangeOrderCountOrder(int orderCount) {
        assertThatThrownBy(() -> OrderItem.of(MenuItem.BARBECUE_RIBS, orderCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 입력은 생성 시 검증을 통과한다")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 20})
    void testValidRangeOrderCountOrder(int orderCount) {
        assertThatNoException().isThrownBy(() -> OrderItem.of(MenuItem.BARBECUE_RIBS, orderCount));
    }
}