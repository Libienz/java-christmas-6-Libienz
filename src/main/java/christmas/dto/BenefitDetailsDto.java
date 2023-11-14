package christmas.dto;

import christmas.domain.DiscountDetail;
import christmas.domain.FreeGifts;
import java.util.List;
import java.util.stream.Stream;

public class BenefitDetailsDto {
    private final List<BenefitDetailDto> benefitDetailDtos;

    private BenefitDetailsDto(List<BenefitDetailDto> benefitDetailDtos) {
        this.benefitDetailDtos = benefitDetailDtos;
    }

    public static BenefitDetailsDto from(List<BenefitDetailDto> benefitDetailDtos) {
        return new BenefitDetailsDto(benefitDetailDtos);
    }

    public static BenefitDetailsDto of(DiscountDetail discountDetail, FreeGifts freeGifts) {
        List<BenefitDetailDto> combinedBenefits = combineToBenefitDetails(discountDetail, freeGifts);
        return BenefitDetailsDto.from(combinedBenefits);
    }

    private static List<BenefitDetailDto> combineToBenefitDetails(DiscountDetail discountDetail,
                                                                  FreeGifts freeGifts) {
        return Stream.concat(
                discountDetail.getDiscountResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDiscountDescription(),
                                result.getDiscountAmount())),
                freeGifts.getGiveawayResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDescription(), result.getDiscountAmount()))
        ).toList();
    }

    public List<BenefitDetailDto> getBenefitDetailDtos() {
        return benefitDetailDtos;
    }

}
