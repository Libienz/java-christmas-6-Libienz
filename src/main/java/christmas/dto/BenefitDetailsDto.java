package christmas.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitDetailsDto {
    private final List<BenefitDetailDto> benefitDetailDtos;

    private BenefitDetailsDto(List<BenefitDetailDto> benefitDetailDtos) {
        this.benefitDetailDtos = benefitDetailDtos;
    }

    public static BenefitDetailsDto of(List<BenefitDetailDto> discountDetails, List<BenefitDetailDto> freeGifts) {
        List<BenefitDetailDto> combined = Stream.concat(discountDetails.stream(), freeGifts.stream())
                .collect(Collectors.toList());
        return new BenefitDetailsDto(combined);
    }

    public List<BenefitDetailDto> getBenefitDetailDtos() {
        return benefitDetailDtos;
    }
}
