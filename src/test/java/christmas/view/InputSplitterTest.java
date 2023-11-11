package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 입력 분리기 테스트")
class InputSplitterTest {
    private InputSplitter inputSplitter;

    @BeforeEach
    void setUp() {
        inputSplitter = new InputSplitter();
    }

    @DisplayName("쉼표를 기반으로 주문 목록을 구분할 수 있다")
    @Test
    void testSplitToMenuWithCounts() {
        String input = "타파스-2,제로콜라-1,아메리카노-3";
        List<String> result = inputSplitter.splitToMenuWithCounts(input);
        assertThat(result).containsExactly("타파스-2", "제로콜라-1", "아메리카노-3");
    }

    @DisplayName("'-'를 기반으로 분리해서 주문메뉴를 추출할 수 있다")
    @Test
    void testSplitToMenu() {
        String input = "타파스-2";
        String result = inputSplitter.splitToMenu(input);
        assertThat(result).isEqualTo("타파스");
    }

    @DisplayName("'-'를 기반으로 분리해서 주문개수를 추출할 수 있다")
    @Test
    void testSplitToCount() {
        String input = "타파스-2";
        String result = inputSplitter.splitToCount(input);
        assertThat(result).isEqualTo("2");
    }
}