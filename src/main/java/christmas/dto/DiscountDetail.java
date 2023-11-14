package christmas.dto;

import java.util.List;

public class DiscountDetail {

    private final List<DiscountDetails> discountDetails;

    private DiscountDetail(List<DiscountDetails> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public static DiscountDetail from(List<DiscountDetails> discountDetails) {
        return new DiscountDetail(discountDetails);
    }

    public List<DiscountDetails> getDiscountResultDtos() {
        return discountDetails;
    }
}
