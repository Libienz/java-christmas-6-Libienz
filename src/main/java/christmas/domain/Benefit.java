package christmas.domain;

import christmas.dto.BenefitDetailsDto;
import christmas.dto.FreeGiftsDto;

public class Benefit {
    private final DiscountDetails discountDetails;
    private final FreeGifts freeGifts;

    public Benefit(DiscountDetails discountDetails, FreeGifts freeGifts) {
        this.discountDetails = discountDetails;
        this.freeGifts = freeGifts;
    }

    public static Benefit of(DiscountDetails discountDetails, FreeGifts freeGifts) {
        return new Benefit(discountDetails, freeGifts);
    }

    public BenefitDetailsDto toBenefitDetailsDto(Order order) {
        return BenefitDetailsDto.of(discountDetails, freeGifts);
    }

    public Integer calculateTotalBenefitAmount(BenefitDetailsDto benefitDetailsDto) {
        return discountDetails.calculateDiscountedPrice() + freeGifts.calculateFreeGiftsPrice();
    }

    public Integer calculateBenefitAppliedPrice(int originalPrice) {
        return originalPrice - discountDetails.calculateDiscountedPrice();
    }

    public FreeGiftsDto toFreeGiftsDto() {
        return freeGifts.toFreeGiftsDto();
    }

    public DecemberEventBadge calculateAchievableBadge(int benefitAmount) {
        return DecemberEventBadge.from(benefitAmount);
    }
}
