package christmas.domain;

import christmas.dto.BenefitDetailsDto;

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

}
