package christmas.service;

import christmas.domain.DecemberEventBadge;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import christmas.domain.FreeGifts;
import christmas.domain.Order;
import christmas.dto.BenefitDetailDto;
import christmas.dto.BenefitDetailsDto;
import java.util.List;

public class BenefitCalculationService {
    private final DiscountService discountService;
    private final GiveawayService giveawayService;

    public BenefitCalculationService(DiscountService discountService, GiveawayService giveawayService) {
        this.discountService = discountService;
        this.giveawayService = giveawayService;
    }


    public BenefitDetailsDto calculateBenefitDetails(Order order) {
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        FreeGifts freeGifts = giveawayService.applyGiveaway(order);
        return BenefitDetailsDto.of(discountDetail, freeGifts);
    }

    public Integer calculateTotalBenefitAmount(BenefitDetailsDto benefitDetailsDto) {
        List<BenefitDetailDto> benefitDetailDtos = benefitDetailsDto.getBenefitDetailDtos();
        return benefitDetailDtos.stream()
                .mapToInt(BenefitDetailDto::getBenefitAmount)
                .sum();
    }

    public Integer calculateBenefitAppliedPrice(Order order) {
        int originalPrice = order.calculatePrice();
        DiscountDetails discountDetail = discountService.applyDiscount(order);
        List<DiscountDetail> discountDetails = discountDetail.getDiscountResultDtos();
        int discountedPrice = discountDetail.getDiscountResultDtos().stream()
                .mapToInt(DiscountDetail::getDiscountAmount)
                .sum();

        return originalPrice - discountedPrice;
    }

    public FreeGifts calculateGiveaway(Order order) {
        return giveawayService.applyGiveaway(order);
    }

    public DecemberEventBadge calculateAchievableBadge(int benefitAmount) {
        return DecemberEventBadge.from(benefitAmount);
    }

}
