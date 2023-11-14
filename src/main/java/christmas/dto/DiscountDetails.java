package christmas.dto;

public class DiscountDetails {
    private final String discountDescription;
    private final Integer discountAmount;

    private DiscountDetails(String discountDescription, Integer discountAmount) {
        this.discountDescription = discountDescription;
        this.discountAmount = discountAmount;
    }

    public static DiscountDetails of(String discountDescription, Integer discountAmount) {
        return new DiscountDetails(discountDescription, discountAmount);
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }
}
