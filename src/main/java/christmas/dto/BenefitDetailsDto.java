package christmas.dto;

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

    public static BenefitDetailsDto of(DiscountDetail discountDetail, GiveawayResultsDto giveawayResultsDto) {
        List<BenefitDetailDto> combinedBenefits = combineToBenefitDetails(discountDetail, giveawayResultsDto);
        return BenefitDetailsDto.from(combinedBenefits);
    }

    private static List<BenefitDetailDto> combineToBenefitDetails(DiscountDetail discountDetail,
                                                                  GiveawayResultsDto giveawayResultsDto) {
        return Stream.concat(
                discountDetail.getDiscountResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDiscountDescription(),
                                result.getDiscountAmount())),
                giveawayResultsDto.getGiveawayResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDescription(), result.getDiscountAmount()))
        ).toList();
    }

    public List<BenefitDetailDto> getBenefitDetailDtos() {
        return benefitDetailDtos;
    }

}
