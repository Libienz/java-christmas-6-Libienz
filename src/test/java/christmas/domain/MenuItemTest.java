package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("메뉴 목록 테스트")
class MenuItemTest {
    @DisplayName("이름에 맞는 메뉴를 찾아올 수 있다")
    @ParameterizedTest
    @MethodSource("menuNameAndExpectedMenu")
    void testFindMenuByMenuName(String menuName, MenuItem expectedMenu) {
        assertThat(MenuItem.from(menuName)).isEqualTo(expectedMenu);
    }

    private static Collection<Arguments> menuNameAndExpectedMenu() {
        return Arrays.asList(
                Arguments.of("양송이수프", MenuItem.MUSHROOM_SOUP),
                Arguments.of("타파스", MenuItem.TAPAS),
                Arguments.of("시저샐러드", MenuItem.CAESAR_SALAD),

                Arguments.of("티본스테이크", MenuItem.T_BONE_STEAK),
                Arguments.of("바비큐립", MenuItem.BARBECUE_RIBS),
                Arguments.of("해산물파스타", MenuItem.SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", MenuItem.CHRISTMAS_PASTA),

                Arguments.of("초코케이크", MenuItem.CHOCOLATE_CAKE),
                Arguments.of("아이스크림", MenuItem.ICE_CREAM),
                Arguments.of("제로콜라", MenuItem.ZERO_COLA),
                Arguments.of("레드와인", MenuItem.RED_WINE),
                Arguments.of("샴페인", MenuItem.CHAMPAGNE));
    }

}