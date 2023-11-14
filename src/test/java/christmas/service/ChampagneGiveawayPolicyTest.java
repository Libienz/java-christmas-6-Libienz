package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItem;
import christmas.domain.OrderItems;
import christmas.domain.FreeGift;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("샴페인 증정 테스트 ")
class ChampagneGiveawayPolicyTest {
    private ChampagneGiveawayPolicy champagneGiveawayPolicy;

    @BeforeEach
    void setUp() {
        champagneGiveawayPolicy = new ChampagneGiveawayPolicy();
    }

    @DisplayName("총 주문 금액이 120000원 이상이면 샴페인을 증정할 수 있다")
    @Test
    void testChampagneGiveawayAchievable() {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(champagneGiveawayPolicy.supports(order)).isTrue();
    }

    @DisplayName("총 주문 금액이 120000원 미만이면 샴페인을 증정 받지 못한다")
    @Test
    void testChampagneGiveawayNonAchievable() {
        OrderItem item1 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(champagneGiveawayPolicy.supports(order)).isFalse();
    }

    @DisplayName("총 주문 금액이 10000원을 넘지 않으면 이벤트 적용이 되지 않는다")
    @Test
    void testEventApplyThresholdMoney() {
        OrderItem item1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 1);
        List<OrderItem> orderItems = List.of(item1);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        assertThat(champagneGiveawayPolicy.supports(order)).isFalse();
    }

    @DisplayName("증정하는 물품은 샴페인이다")
    @Test
    void testGiveawayMenu() {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        FreeGift freeGift = champagneGiveawayPolicy.applyGiveaway(order);

        assertThat(freeGift.getGiveaway()).isEqualTo(MenuItem.CHAMPAGNE);
    }

    @DisplayName("증정되는 개수는 1개이다")
    @Test
    void testGiveawayMenuCount() {
        OrderItem item1 = OrderItem.of(MenuItem.BARBECUE_RIBS, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(5));
        FreeGift freeGift = champagneGiveawayPolicy.applyGiveaway(order);

        assertThat(freeGift.getCount()).isEqualTo(1);
    }
}