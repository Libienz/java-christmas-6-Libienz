package christmas.service;

import christmas.domain.DecemberEventBadge;
import christmas.domain.Order;
import christmas.dto.BenefitDetailDto;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.DiscountResultDto;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultsDto;
import java.util.List;

public class AppliedBenefitCalculatorService {

    private final DiscountService discountService;
    private final GiveawayService giveawayService;

    public AppliedBenefitCalculatorService(DiscountService discountService, GiveawayService giveawayService) {
        this.discountService = discountService;
        this.giveawayService = giveawayService;
    }

    public BenefitDetailsDto calculateBenefitDetails(Order order) {
        DiscountResultsDto discountResultsDto = discountService.applyDiscount(order);
        GiveawayResultsDto giveawayResultsDto = giveawayService.applyGiveaway(order);
        return BenefitDetailsDto.of(discountResultsDto, giveawayResultsDto);
    }

    public Integer calculateTotalBenefitAmount(BenefitDetailsDto benefitDetailsDto) {
        List<BenefitDetailDto> benefitDetailDtos = benefitDetailsDto.getBenefitDetailDtos();
        return benefitDetailDtos.stream()
                .mapToInt(BenefitDetailDto::getBenefitAmount)
                .sum();
    }

    public Integer calculateBenefitAppliedPrice(Order order) {
        int originalPrice = order.calculatePrice();
        DiscountResultsDto discountResultsDto = discountService.applyDiscount(order);
        List<DiscountResultDto> discountResultDtos = discountResultsDto.getDiscountResultDtos();
        int discountedPrice = discountResultsDto.getDiscountResultDtos().stream()
                .mapToInt(DiscountResultDto::getDiscountAmount)
                .sum();

        return originalPrice - discountedPrice;
    }

    public GiveawayResultsDto calculateGiveaway(Order order) {
        return giveawayService.applyGiveaway(order);
    }

    public DecemberEventBadge calculateAchievableBadge(int benefitAmount) {
        return DecemberEventBadge.from(benefitAmount);
    }

}
