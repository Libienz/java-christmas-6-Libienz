package christmas.service;

import christmas.domain.DecemberEventBadge;
import christmas.dto.DiscountResultDto;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultsDto;

public class AppliedBenefitCalculatorService {
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
