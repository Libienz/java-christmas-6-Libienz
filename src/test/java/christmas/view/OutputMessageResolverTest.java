package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DecemberEventBadge;
import christmas.domain.benefit.DiscountDetail;
import christmas.domain.benefit.DiscountDetails;
import christmas.domain.benefit.FreeGift;
import christmas.domain.benefit.FreeGifts;
import christmas.domain.menu.MenuItem;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.dto.EventBadgeDto;
import christmas.dto.OrderItemsDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("출력 메시지 리졸버 테스트")
class OutputMessageResolverTest {
    private OutputMessageResolver outputMessageResolver;
    private static final String LINE_SEPARATOR = System.lineSeparator();

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
        assertThat(message.trim()).isEqualTo(
                ("<주문 메뉴>" + LINE_SEPARATOR
                        + "티본스테이크 3개" + LINE_SEPARATOR
                        + "해산물파스타 3개" + LINE_SEPARATOR
                        + "초코케이크 3개").trim());

    }

    @DisplayName("할인 전 총주문 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testOriginalPriceMessageResolve() {
        String message = outputMessageResolver.resolveOriginalPriceMessage(3000);
        assertThat(message.trim()).isEqualTo(("<할인 전 총주문 금액>" + LINE_SEPARATOR + "3,000원").trim());
    }

    @DisplayName("증정 메뉴 메시지를 규칙에 맞게 생성한다")
    @Test
    void testGiveawayMessageResolve() {
        FreeGift freeGift = FreeGift.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");
        FreeGifts freeGifts = FreeGifts.from(List.of(freeGift));

        String message = outputMessageResolver.resolveGiveawayMessage(freeGifts.toFreeGiftsDto());
        assertThat(message.trim()).isEqualTo(("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개").trim());
    }

    @DisplayName("혜택 내역 메시지를 규칙에 맞게 생성한다")
    @Test
    void testBenefitAppliedMessageResolve() {
        DiscountDetail discount1 = DiscountDetail.of("크리스마스 디데이 할인", 1200);
        DiscountDetail discount2 = DiscountDetail.of("평일 할인", 4046);
        DiscountDetail discount3 = DiscountDetail.of("특별 할인", 1000);
        FreeGift freeGift = FreeGift.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");

        DiscountDetails discountDetails = DiscountDetails.from(List.of(discount1, discount2, discount3));
        FreeGifts freeGifts = FreeGifts.from(List.of(freeGift));

        Benefit benefit = Benefit.of(discountDetails, freeGifts);
        String message = outputMessageResolver.resolveAppliedBenefitMessage(benefit.toBenefitDetailsDto());
        assertThat(message.trim()).isEqualTo(
                ("<혜택 내역>" + LINE_SEPARATOR
                        + "크리스마스 디데이 할인: -1,200원" + LINE_SEPARATOR
                        + "평일 할인: -4,046원" + LINE_SEPARATOR
                        + "특별 할인: -1,000원" + LINE_SEPARATOR
                        + "증정 이벤트: -25,000원").trim());
    }

    @DisplayName("총혜택 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testBenefitAmountMessageResolve() {
        String message = outputMessageResolver.resolveBenefitAmountMessage(31246);
        assertThat(message.trim()).isEqualTo("<총혜택 금액>" + LINE_SEPARATOR + "-31,246원");
    }

    @DisplayName("할인 후 예상 결제 금액 메시지를 규칙에 맞게 생성한다")
    @Test
    void testDiscountedPriceMessageResolve() {
        String message = outputMessageResolver.resolveDiscountedPriceMessage(135754);
        assertThat(message.trim()).isEqualTo(("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원").trim());
    }

    @DisplayName("12월 이벤트 배지 메시지를 규칙에 맞게 생성한다")
    @Test
    void testEventBadgeMessageResolve() {
        String message = outputMessageResolver.resolveEventBadgeMessage(
                EventBadgeDto.from(DecemberEventBadge.SANTA.getItemName()));
        assertThat(message.trim()).isEqualTo(("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타").trim());
    }
}