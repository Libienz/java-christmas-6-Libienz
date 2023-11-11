package christmas.view;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("입력 포맷 검증기 테스트")
class InputFormatValidatorTest {
    private InputFormatValidator inputFormatValidator;

    @BeforeEach
    void setUp() {
        inputFormatValidator = new InputFormatValidator();
    }

    @DisplayName("날짜 입력이 숫자가 아니라면 검증을 통과하지 못한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "1a"})
    void testNonNumericDateInputValidation(String input) {
        assertThatThrownBy(() -> inputFormatValidator.validateOrderDateInputFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 날짜 입력은 검증을 통과한다")
    @ParameterizedTest
    @ValueSource(strings = {"12", "13", "14"})
    void testNumericDateInputValidation(String input) {
        assertThatNoException().isThrownBy(() -> inputFormatValidator.validateOrderDateInputFormat(input));
    }

    @DisplayName("주문 입력에 공백이 있으면 검증을 통과하지 못한다")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2, 시저샐러드-1", "양송이수프-2,시저샐러드 -1", "양송이수프 -2,시저샐러드-1"})
    void testMalFormedOrderWhiteSpaceInputValidation(String input) {
        assertThatThrownBy(() -> inputFormatValidator.validateOrdersInputFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 입력이 ','로 구분되지 않으면 검증을 통과하지 못한다")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2.시저샐러드-1", "양송이수프-2:시저샐러드-1", "양송이수프-2;시저샐러드-1"})
    void testMalFormedOrderNoSeparatorInputValidation(String input) {
        assertThatThrownBy(() -> inputFormatValidator.validateOrdersInputFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 개수를 '-'로 표시하지 않으면 검증을 통과하지 못한다")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프+2,시저샐러드+1", "양송이수프:2, 시저샐러드:1", "양송이수프#2,시저샐러드-1"})
    void testMalFormedOrderNoCountIndicatorInputValidation(String input) {
        assertThatThrownBy(() -> inputFormatValidator.validateOrdersInputFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 주문 입력은 검증을 통과한다")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2,시저샐러드-1", "양송이수프-3,티본스테이크-1", "티본스테이크-2,시저샐러드-1"})
    void testFormedOrderInputValidation(String input) {
        assertThatNoException().isThrownBy(() -> inputFormatValidator.validateOrdersInputFormat(input));
    }
}