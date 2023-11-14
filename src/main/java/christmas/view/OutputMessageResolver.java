package christmas.view;

import christmas.domain.DecemberEventBadge;
import christmas.dto.BenefitDetailDto;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiveawayResultDto;
import christmas.dto.GiveawayResultsDto;
import christmas.dto.OrderDateDto;
import christmas.dto.OrderItemsDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputMessageResolver {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_ITEMS_MESSAGE_PREFIX = LINE_SEPARATOR + "<주문 메뉴>" + LINE_SEPARATOR;
    private static final String GIVEAWAY_MESSAGE_PREFIX = LINE_SEPARATOR + "<증정 메뉴>" + LINE_SEPARATOR;
    private static final String APPLIED_BENEFIT_MESSAGE_PREFIX = LINE_SEPARATOR + "<혜택 내역>" + LINE_SEPARATOR;
    private static final String ORIGINAL_PRICE_MESSAGE_PREFIX = LINE_SEPARATOR + "<할인 전 총주문 금액>" + LINE_SEPARATOR;
    private static final String BENEFIT_AMOUNT_MESSAGE_PREFIX = LINE_SEPARATOR + "<총혜택 금액>" + LINE_SEPARATOR;
    private static final String DISCOUNTED_PRICE_MESSAGE_PREFIX = LINE_SEPARATOR + "<할인 후 예상 결제 금액>" + LINE_SEPARATOR;
    private static final String EVENT_BADGE_MESSAGE_PREFIX = LINE_SEPARATOR + "<12월 이벤트 배지>" + LINE_SEPARATOR;
    private static final String RESULT_EMPTY_MESSAGE = "없음";

    public String resolvePreviewMessage(OrderDateDto orderDateDto) {
        return String.format(EVENT_PREVIEW_MESSAGE, orderDateDto.getDayOfMonth());
    }

    public String resolveOrderItemsMessage(OrderItemsDto orderItemsDto) {
        Map<String, Integer> orderItems = orderItemsDto.getOrderItems();
        return ORDER_ITEMS_MESSAGE_PREFIX + orderItems.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public String resolveOriginalPriceMessage(Integer originalPrice) {
        return ORIGINAL_PRICE_MESSAGE_PREFIX + formatCurrency(originalPrice);
    }

    public String resolveGiveawayMessage(GiveawayResultsDto giveawayResultsDto) {
        List<GiveawayResultDto> giveawayResultDtos = giveawayResultsDto.getGiveawayResultDtos();
        if (giveawayResultDtos.isEmpty()) {
            return GIVEAWAY_MESSAGE_PREFIX + RESULT_EMPTY_MESSAGE;
        }
        return GIVEAWAY_MESSAGE_PREFIX + giveawayResultDtos.stream()
                .map(resultDto -> resultDto.getGiveaway().getItemName() + " " + resultDto.getCount() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public String resolveAppliedBenefitMessage(BenefitDetailsDto benefitDetailsDto) {
        List<BenefitDetailDto> benefitDetailDtos = benefitDetailsDto.getBenefitDetailDtos();
        if (benefitDetailDtos.isEmpty()) {
            return APPLIED_BENEFIT_MESSAGE_PREFIX + RESULT_EMPTY_MESSAGE;
        }
        return APPLIED_BENEFIT_MESSAGE_PREFIX + benefitDetailDtos.stream()
                .map(detailDto -> String.format("%s: -%,d원", detailDto.getDescription(), detailDto.getBenefitAmount()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public String resolveBenefitAmountMessage(int benefitAmount) {
        if (benefitAmount == 0) {
            return BENEFIT_AMOUNT_MESSAGE_PREFIX + formatCurrency(benefitAmount);
        }
        return BENEFIT_AMOUNT_MESSAGE_PREFIX + "-" + formatCurrency(benefitAmount);
    }

    public String resolveDiscountedPriceMessage(int price) {
        return DISCOUNTED_PRICE_MESSAGE_PREFIX + formatCurrency(price);
    }

    public String resolveEventBadgeMessage(DecemberEventBadge decemberEventBadge) {
        return EVENT_BADGE_MESSAGE_PREFIX + decemberEventBadge.getItemName();
    }

    private String formatCurrency(int amount) {
        return String.format("%,d원", amount);
    }

}
