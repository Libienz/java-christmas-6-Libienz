package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.dto.order.OrderDateDto;
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

    @DisplayName("날짜가 주어지면 객체와 주어진 날짜의 변위를 계산할 수 있다")
    @Test
    void testCalculateOffset() {
        OrderDate orderDate = OrderDate.from(5);
        assertThat(orderDate.calculateDayOffset(1)).isEqualTo(4);
    }

    @DisplayName("주말인 경우 알아낼 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 29, 30})
    void testDetermineWeekend(int day) {
        OrderDate orderDate = OrderDate.from(day);
        assertThat(orderDate.isWeekend()).isTrue();
    }

    @DisplayName("주말인 경우 알아낼 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {12, 13, 28, 24})
    void testDetermineWeekDay(int day) {
        OrderDate orderDate = OrderDate.from(day);
        assertThat(orderDate.isWeekend()).isFalse();
    }

    @DisplayName("주문 날짜 DTO로 변환 가능하다")
    @Test
    void testCreateOrderDateDto() {
        OrderDateDto orderDateDto = OrderDate.from(31).toOrderDateDto();
        assertThat(orderDateDto.getDayOfMonth()).isEqualTo(31);
    }

    @DisplayName("특정 날짜와 같은지 비교할 수 있다")
    @Test
    void testSameDateEvaluation() {
        OrderDate orderDate = OrderDate.from(31);
        assertThat(orderDate.isSameDate(31)).isTrue();
    }
}