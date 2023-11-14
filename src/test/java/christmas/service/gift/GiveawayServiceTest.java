package christmas.service.gift;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.FreeGifts;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.dto.FreeGiftDto;
import christmas.dto.FreeGiftsDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("증정 서비스 테스트")
class GiveawayServiceTest {
    private GiveawayService giveawayService;

    @BeforeEach
    void setUp() {
        List<GiveawayPolicy> giveawayPolicies = List.of(new ChampagneGiveawayPolicy());
        giveawayService = new GiveawayService(giveawayPolicies);
    }

    @DisplayName("샴페인 증정 정책만을 만족할 경우 적용된 증정은 1개이다")
    @Test
    void testAppliedGiveawayCount() {
        OrderItem item1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        FreeGifts giveawayResultsDto = giveawayService.applyGiveaway(order);
        FreeGiftsDto freeGifts = giveawayResultsDto.toFreeGiftsDto();

        assertThat(freeGifts.getFreeGiftDtos().size()).isEqualTo(1);
    }

    @DisplayName("샴페인 증정 정책만을 만족할 경우 샴페인이 증정된다")
    @Test
    void testAppliedGiveawayMenuItem() {
        OrderItem item1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        FreeGifts giveawayResultsDto = giveawayService.applyGiveaway(order);
        List<FreeGiftDto> freeGifts = giveawayResultsDto.toFreeGiftsDto().getFreeGiftDtos();

        assertThat(freeGifts.get(0).getMenuName()).isEqualTo(MenuItem.CHAMPAGNE.getItemName());
    }

    @DisplayName("샴페인 증정 정책만을 만족할 경우 증정 개수는 1개이다")
    @Test
    void testAppliedGiveawayMenuItemCount() {
        OrderItem item1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        FreeGifts giveawayResultsDto = giveawayService.applyGiveaway(order);
        List<FreeGiftDto> freeGifts = giveawayResultsDto.toFreeGiftsDto().getFreeGiftDtos();

        assertThat(freeGifts.get(0).getCount()).isEqualTo(1);
    }
}