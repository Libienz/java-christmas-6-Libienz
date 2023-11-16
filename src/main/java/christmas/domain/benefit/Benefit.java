package christmas.domain.benefit;

import christmas.dto.benefit.BenefitDetailDto;
import christmas.dto.benefit.BenefitDetailsDto;
import christmas.dto.benefit.FreeGiftsDto;
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

    public Integer calculateDiscountedAmount() {
        return discountDetails.calculateDiscountedPrice();
    }

    public FreeGiftsDto toFreeGiftsDto() {
        return freeGifts.toFreeGiftsDto();
    }

    public DecemberEventBadge calculateAchievableBadge() {
        return DecemberEventBadge.from(calculateTotalBenefitAmount());
    }

    private List<BenefitDetailDto> combineToBenefitDetailDtos() {
        return Stream.concat(discountDetails.toBenefitDetailDtos().stream(), freeGifts.toBenefitDetailDtos().stream())
                .collect(Collectors.toList());
    }
}
