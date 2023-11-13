package christmas.dto;

import java.util.List;

public class BenefitDetailsDto {
    private final List<BenefitDetailDto> benefitDetailDtos;

    private BenefitDetailsDto(List<BenefitDetailDto> benefitDetailDtos) {
        this.benefitDetailDtos = benefitDetailDtos;
    }

    public static BenefitDetailsDto from(List<BenefitDetailDto> benefitDetailDtos) {
        return new BenefitDetailsDto(benefitDetailDtos);
    }

    public List<BenefitDetailDto> getBenefitDetailDtos() {
        return benefitDetailDtos;
    }
}
