package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import christmas.dto.order.OrderItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("특정 카테고리 상품의 주문 개수가 몇개인지 계산할 수 있다")
    @Test
    void testCountCategoryItems() {
        OrderItem orderItem = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        assertThat(orderItem.countCategoryItem(MenuCategory.BEVERAGE)).isEqualTo(3);
    }

    @DisplayName("음료 주문인 경우를 알아낼 수 있다")
    @Test
    void testIsBeverageOrder() {
        OrderItem orderItem = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        assertThat(orderItem.isBeverageOrder()).isTrue();
    }

    @DisplayName("음료 주문이 아닌 경우를 알아낼 수 있다")
    @Test
    void testIsNotBeverageOrder() {
        OrderItem orderItem = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        assertThat(orderItem.isBeverageOrder()).isFalse();
    }

    @DisplayName("주문 상품 DTO로 변환할 수 있다")
    @Test
    void testCreateOrderItemDto() {
        OrderItemDto orderItem = OrderItem.of(MenuItem.BARBECUE_RIBS, 3).toOrderItemDto();
        assertThat(orderItem.getMenuName()).isEqualTo("바비큐립");
    }
}