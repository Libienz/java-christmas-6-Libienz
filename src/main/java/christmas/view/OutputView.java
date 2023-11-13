package christmas.view;

import christmas.domain.DecemberEventBadge;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultsDto;
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

    public void printGiveaway(GiveawayResultsDto giveawayResultsDto) {
        System.out.println(outputMessageResolver.resolveGiveawayMessage(giveawayResultsDto));
    }

    public void printAppliedBenefitInformation(DiscountResultsDto discountResultsDto,
                                               GiveawayResultsDto giveawayResultsDto) {
        System.out.println(outputMessageResolver.resolveAppliedBenefitMessage(discountResultsDto, giveawayResultsDto));
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
