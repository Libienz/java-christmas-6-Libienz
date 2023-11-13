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

    public static BenefitDetailsDto of(DiscountResultsDto discountResultsDto, GiveawayResultsDto giveawayResultsDto) {
        List<BenefitDetailDto> combinedBenefits = combineToBenefitDetails(discountResultsDto, giveawayResultsDto);
        return BenefitDetailsDto.from(combinedBenefits);
    }

    private static List<BenefitDetailDto> combineToBenefitDetails(DiscountResultsDto discountResultsDto,
                                                                  GiveawayResultsDto giveawayResultsDto) {
        List<BenefitDetailDto> combinedBenefits = Stream.concat(
                discountResultsDto.getDiscountResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDiscountDescription(),
                                result.getDiscountAmount())),
                giveawayResultsDto.getGiveawayResultDtos().stream()
                        .map(result -> BenefitDetailDto.of(result.getDescription(), result.getDiscountAmount()))
        ).toList();
        return combinedBenefits;
    }

    public List<BenefitDetailDto> getBenefitDetailDtos() {
        return benefitDetailDtos;
    }

}
