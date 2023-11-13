package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DecemberEventBadge;
import christmas.domain.MenuItem;
import christmas.domain.OrderDate;
import christmas.domain.OrderItem;
import christmas.domain.OrderItems;
import christmas.dto.DiscountResultDto;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultDto;
import christmas.dto.GiveawayResultsDto;
import christmas.dto.OrderItemsDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("출력 메시지 리졸버 테스트")
class OutputMessageResolverTest {
    private OutputMessageResolver outputMessageResolver;

    @BeforeEach
    void setUp() {
        outputMessageResolver = new OutputMessageResolver();
    }

    @DisplayName("결과 전 출력하는 프리뷰 메시지를 규칙에 맞게 생성한다")
    @Test
    void testPreviewMessageResolve() {
        String message = outputMessageResolver.resolvePreviewMessage(OrderDate.from(3).toOrderDateDto());
        assertThat(message.trim()).isEqualTo("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".trim());
    }

    @DisplayName("주문 메뉴 메시지를 규칙에 맞게 생성한다")
    @Test
    void testOrderItemsMessageResolve() {
        OrderItem orderItem1 = OrderItem.of(MenuItem.T_BONE_STEAK, 3);
        OrderItem orderItem2 = OrderItem.of(MenuItem.SEAFOOD_PASTA, 3);
        OrderItem orderItem3 = OrderItem.of(MenuItem.CHOCOLATE_CAKE, 3);

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3);
        OrderItemsDto orderItemsDto = OrderItems.from(orderItems).toOrderItemsDto();

        String message = outputMessageResolver.resolveOrderItemsMessage(orderItemsDto);
        assertThat(message.trim()).isEqualTo("<주문 메뉴>\n티본스테이크 3개\n해산물파스타 3개\n초코케이크 3개\n".trim());
    }

    @DisplayName("할인 전 총주문 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testOriginalPriceMessageResolve() {
        String message = outputMessageResolver.resolveOriginalPriceMessage(3000);
        assertThat(message.trim()).isEqualTo("<할인 전 총주문 금액>\n3,000원".trim());
    }

    @DisplayName("증정 메뉴 메시지를 규칙에 맞게 생성한다")
    @Test
    void testGiveawayMessageResolve() {
        GiveawayResultDto giveawayResultDto = GiveawayResultDto.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");
        GiveawayResultsDto giveawayResultsDto = GiveawayResultsDto.from(List.of(giveawayResultDto));

        String message = outputMessageResolver.resolveGiveawayMessage(giveawayResultsDto);
        assertThat(message.trim()).isEqualTo("<증정 메뉴>\n샴페인 1개".trim());
    }

    @DisplayName("혜택 내역 메시지를 규칙에 맞게 생성한다")
    @Test
    void testBenefitAppliedMessageResolve() {
        DiscountResultDto discount1 = DiscountResultDto.of("크리스마스 디데이 할인", 1200);
        DiscountResultDto discount2 = DiscountResultDto.of("평일 할인", 4046);
        DiscountResultDto discount3 = DiscountResultDto.of("특별 할인", 1000);
        GiveawayResultDto giveawayResultDto = GiveawayResultDto.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");

        DiscountResultsDto discountResultsDto = DiscountResultsDto.from(List.of(discount1, discount2, discount3));
        GiveawayResultsDto giveawayResultsDto = GiveawayResultsDto.from(List.of(giveawayResultDto));

        String message = outputMessageResolver.resolveAppliedBenefitMessage(discountResultsDto, giveawayResultsDto);
        assertThat(message.trim()).isEqualTo(
                "<혜택 내역>\n크리스마스 디데이 할인: -1,200원\n평일 할인: -4,046원\n특별 할인: -1,000원\n증정 이벤트: -25,000원".trim());
    }

    @DisplayName("총혜택 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testBenefitAmountMessageResolve() {
        String message = outputMessageResolver.resolveBenefitAmountMessage(31246);
        assertThat(message.trim()).isEqualTo("<총혜택 금액>\n-31,246원");
    }

    @DisplayName("할인 후 예상 결제 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testDiscountedPriceMessageResolve() {
        String message = outputMessageResolver.resolveDiscountedPriceMessage(135754);
        assertThat(message.trim()).isEqualTo("<할인 후 예상 결제 금액>\n135,754원".trim());
    }

    @DisplayName("12월 이벤트 배지 메시지를 규칙에 맞게 생성한다")
    @Test
    void testEventBadgeMessageResolve() {
        String message = outputMessageResolver.resolveEventBadgeMessage(DecemberEventBadge.SANTA);
        assertThat(message).isEqualTo("<12월 이벤트 배지>\n산타");
    }
}