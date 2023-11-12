package christmas.dto;

public class DiscountResultDto {
    private final String discountDescription;
    private final Integer discountAmount;

    private DiscountResultDto(String discountDescription, Integer discountAmount) {
        this.discountDescription = discountDescription;
        this.discountAmount = discountAmount;
    }

    public static DiscountResultDto of(String discountDescription, Integer discountAmount) {
        return new DiscountResultDto(discountDescription, discountAmount);
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }
}
