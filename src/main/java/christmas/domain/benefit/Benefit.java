package christmas.domain.benefit;

import christmas.dto.BenefitDetailDto;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.FreeGiftsDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public BenefitDetailsDto toBenefitDetailsDto() {
        return BenefitDetailsDto.of(combineToBenefitDetailDtos());
    }

    public Integer calculateTotalBenefitAmount() {
        return discountDetails.calculateDiscountedPrice() + freeGifts.calculateFreeGiftsPrice();
    }

    public FreeGiftsDto toFreeGiftsDto() {
        return freeGifts.toFreeGiftsDto();
    }

    public DecemberEventBadge calculateAchievableBadge(int benefitAmount) {
        return DecemberEventBadge.from(benefitAmount);
    }

    private List<BenefitDetailDto> combineToBenefitDetailDtos() {
        return Stream.concat(discountDetails.toBenefitDetailDtos().stream(), freeGifts.toBenefitDetailDtos().stream())
                .collect(Collectors.toList());
    }
}
