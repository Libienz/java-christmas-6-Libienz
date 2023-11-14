package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.dto.OrderItemDto;
import christmas.dto.OrderItemsDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 목록 테스트")
class OrderItemsTest {
    @DisplayName("중복이 있는 경우 생성 시 검증에 실패한다")
    @Test
    void testDuplicateOrderItems() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatThrownBy(() -> OrderItems.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료만 주문 시 주문이 불가하다")
    @Test
    void testBeverageOnlyOrderValidate() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatThrownBy(() -> OrderItems.from(orderItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("올바른 생성 요청은 생성 시 검증을 통과한다")
    @Test
    void testNonDuplicateOrderItems() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.CHAMPAGNE, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatNoException().isThrownBy(() -> OrderItems.from(orderItems));
    }

    @DisplayName("특정 카테고리의 상품이 몇개인지 계산할 수 있다")
    @Test
    void countCategoryItems() {

        OrderItem orderItem1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        OrderItem orderItem3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3);

        assertThat(OrderItems.from(orderItems).countCategoryItem(MenuCategory.MAIN)).isEqualTo(6);
    }

    @DisplayName("총 주문 금액을 계산할 수 있다")
    @Test
    void testCalculateTotalPrice() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        OrderItem orderItem3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3);

        assertThat(OrderItems.from(orderItems).calculateTotalPrice()).isEqualTo(315000);
    }

    @DisplayName("주문 목록 DTO로 변환할 수 있다")
    @Test
    void testCreateOrderItemsDto() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        OrderItem orderItem3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3);
        OrderItemsDto orderItemsDto = OrderItems.from(orderItems).toOrderItemsDto();

        assertThat(orderItemsDto.getOrderItemDtos()).extracting(OrderItemDto::getMenuName)
                .contains("티본스테이크", "해산물파스타", "초코케이크");
    }
}