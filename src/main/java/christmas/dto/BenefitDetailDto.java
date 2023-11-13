package christmas.dto;

public class BenefitDetailDto {
    private final String description;
    private final Integer benefitAmount;

    private BenefitDetailDto(String description, Integer benefitAmount) {
        this.description = description;
        this.benefitAmount = benefitAmount;
    }

    public static BenefitDetailDto of(String description, Integer benefitAmount) {
        return new BenefitDetailDto(description, benefitAmount);
    }

    public String getDescription() {
        return description;
    }

    public Integer getBenefitAmount() {
        return benefitAmount;
    }
}
