package christmas.domain.benefit;

import christmas.domain.benefit.DecemberEventBadge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("12월 이벤트 배지 테스트")
class DecemberEventBadgeTest {

    @DisplayName("혜택 금액 0 ~ 4999 배지 없음")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 4999})
    void testBadgeNone(int benefitAmount) {
        Assertions.assertThat(DecemberEventBadge.from(benefitAmount)).isEqualTo(DecemberEventBadge.NONE);
    }

    @DisplayName("혜택 금액 5000 ~ 9999 별 배지 증정")
    @ParameterizedTest
    @ValueSource(ints = {5000, 6000, 9999})
    void testBadgeStar(int benefitAmount) {
        Assertions.assertThat(DecemberEventBadge.from(benefitAmount)).isEqualTo(DecemberEventBadge.STAR);
    }

    @DisplayName("혜택 금액 10000 ~ 19999 트리 배지 증정")
    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 19999})
    void testBadgeTree(int benefitAmount) {
        Assertions.assertThat(DecemberEventBadge.from(benefitAmount)).isEqualTo(DecemberEventBadge.TREE);
    }

    @DisplayName("혜택 금액 20000이상 산타 배지 증정")
    @ParameterizedTest
    @ValueSource(ints = {20000, 30000, 40000})
    void testBadgeSanta(int benefitAmount) {
        Assertions.assertThat(DecemberEventBadge.from(benefitAmount)).isEqualTo(DecemberEventBadge.SANTA);
    }
}