package christmas.view;

import christmas.domain.benefit.DecemberEventBadge;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.FreeGiftsDto;
import christmas.dto.OrderDateDto;
import christmas.dto.OrderItemsDto;

public class OutputView {
    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private final OutputMessageResolver outputMessageResolver;

    public OutputView(OutputMessageResolver outputMessageResolver) {
        this.outputMessageResolver = outputMessageResolver;
    }

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printEventResultPreview(OrderDateDto orderDateDto) {
        System.out.println(outputMessageResolver.resolvePreviewMessage(orderDateDto));
    }

    public void printOrderItems(OrderItemsDto orderItemsDto) {
        System.out.println(outputMessageResolver.resolveOrderItemsMessage(orderItemsDto));
    }

    public void printOriginalPrice(Integer originalPrice) {
        System.out.println(outputMessageResolver.resolveOriginalPriceMessage(originalPrice));
    }

    public void printGiveaway(FreeGiftsDto freeGifts) {
        System.out.println(outputMessageResolver.resolveGiveawayMessage(freeGifts));
    }

    public void printAppliedBenefitInformation(BenefitDetailsDto benefitDetailsDto) {
        System.out.println(outputMessageResolver.resolveAppliedBenefitMessage(benefitDetailsDto));
    }

    public void printBenefitAmount(Integer benefitAmount) {
        System.out.println(outputMessageResolver.resolveBenefitAmountMessage(benefitAmount));
    }

    public void printDiscountedPrice(Integer discountedPrice) {
        System.out.println(outputMessageResolver.resolveDiscountedPriceMessage(discountedPrice));
    }

    public void printEventBadge(DecemberEventBadge decemberEventBadge) {
        System.out.println(outputMessageResolver.resolveEventBadgeMessage(decemberEventBadge));
    }

}
