package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("주문 날짜 테스트")
class OrderDateTest {
    @DisplayName("범위를 벗어나는 입력은 생성 시 검증에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 32, 33})
    void testInvalidRangeOrderDate(int dayOfDecember) {
        assertThatThrownBy(() -> OrderDate.from(dayOfDecember))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 입력은 생성 시 검증을 통과한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 31})
    void testValidRangeOrderDate(int dayOfDecember) {
        assertThatNoException().isThrownBy(() -> OrderDate.from(dayOfDecember));
    }

    @DisplayName("기간이 주어졌을 때 객체가 그 기간에 포함되는지 판단할 수 있다")
    @Test
    void testIsInPeriod() {
        OrderDate orderDate = OrderDate.from(5);
        assertThat(orderDate.isInPeriod(1, 31)).isTrue();
    }

    @DisplayName("기간이 주어졌을 때 객체가 그 기간에 포함되는지 판단할 수 있다")
    @Test
    void testIsNotInPeriod() {
        OrderDate orderDate = OrderDate.from(5);
        assertThat(orderDate.isInPeriod(1, 4)).isFalse();
    }

}