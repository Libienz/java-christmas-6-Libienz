package christmas.service.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.DiscountDetails;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.dto.BenefitDetailDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("통합 할인 적용 서비스 테스트")
class DiscountServiceTest {
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        List<DiscountPolicy> discountPolicies = List.of(new ChristmasDiscountPolicy(), new SpecialDiscountPolicy(),
                new WeekendDiscountPolicy(), new WeekDayDiscountPolicy());
        discountService = new DiscountService(discountPolicies);
    }

    @DisplayName("아무 할인도 적용되지 않을 때 혜택 내역 개수는 0이다")
    @Test
    void testNoDiscountApplied() {
        OrderItem item1 = OrderItem.of(MenuItem.MUSHROOM_SOUP, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(27));
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        List<BenefitDetailDto> benefitDetailDtos = discountDetail.toBenefitDetailDtos();

        assertThat(benefitDetailDtos.size()).isEqualTo(0);
    }

    @DisplayName("크리스마스 할인, 평일 할인만 적용될 떄 혜택 내역 개수는 2이다")
    @Test
    void testTwoDiscountApplied() {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        List<BenefitDetailDto> benefitDetailDtos = discountDetail.toBenefitDetailDtos();

        assertThat(benefitDetailDtos.size()).isEqualTo(2);
    }

    @DisplayName("할인의 종류를 description으로 알 수 있다")
    @Test
    void testAppliedDiscountDescription() {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        List<BenefitDetailDto> discountDetails = discountDetail.toBenefitDetailDtos();

        assertThat(discountDetails)
                .extracting(BenefitDetailDto::getDescription)
                .contains("평일 할인", "크리스마스 디데이 할인");
    }

    @DisplayName("적용된 할인 금액을 올바르게 계산한다")
    @Test
    void testAppliedDiscountAmount() {
        OrderItem item1 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);
        OrderItem item2 = OrderItem.of(MenuItem.CAESAR_SALAD, 3);
        OrderItem item3 = OrderItem.of(MenuItem.ZERO_COLA, 3);
        List<OrderItem> orderItems = List.of(item1, item2, item3);

        Order order = Order.of(OrderItems.from(orderItems), OrderDate.from(4));
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        List<BenefitDetailDto> discountDetails = discountDetail.toBenefitDetailDtos();

        assertThat(discountDetails)
                .extracting(BenefitDetailDto::getBenefitAmount)
                .contains(2023 * 3, 1300);
    }
}