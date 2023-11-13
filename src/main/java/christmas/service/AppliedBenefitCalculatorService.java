package christmas.service;

import christmas.domain.DecemberEventBadge;
import christmas.domain.Order;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.DiscountResultDto;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultsDto;

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

    public Integer calculateTotalBenefitAmount(DiscountResultsDto discountResultsDto,
                                               GiveawayResultsDto giveawayResultsDto) {
        return calculateTotalDiscountAmount(discountResultsDto) + calculateTotalGiveawayAmount(giveawayResultsDto);
    }

    public Integer calculateBenefitAppliedPrice(int originalPrice, int benefitAmount) {
        return originalPrice - benefitAmount;
    }

    public DecemberEventBadge calculateAchievableBadge(int benefitAmount) {
        return DecemberEventBadge.from(benefitAmount);
    }

    private int calculateTotalGiveawayAmount(GiveawayResultsDto giveawayResultsDto) {
        return giveawayResultsDto.getGiveawayResultDtos().stream()
                .mapToInt(result -> result.getGiveaway().getPrice() * result.getCount())
                .sum();
    }

    private int calculateTotalDiscountAmount(DiscountResultsDto discountResultsDto) {
        return discountResultsDto.getDiscountResultDtos().stream()
                .mapToInt(DiscountResultDto::getDiscountAmount)
                .sum();
    }
}
