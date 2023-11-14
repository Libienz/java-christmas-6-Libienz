package christmas.domain;

public class DiscountDetail {
    private final String discountDescription;
    private final Integer discountAmount;

    private DiscountDetail(String discountDescription, Integer discountAmount) {
        this.discountDescription = discountDescription;
        this.discountAmount = discountAmount;
    }

    public static DiscountDetail of(String discountDescription, Integer discountAmount) {
        return new DiscountDetail(discountDescription, discountAmount);
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }
}
