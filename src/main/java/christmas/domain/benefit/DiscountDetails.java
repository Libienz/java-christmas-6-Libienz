package christmas.domain.benefit;

import christmas.dto.benefit.BenefitDetailDto;
import java.util.List;

public class DiscountDetails {

    private final List<DiscountDetail> discountDetails;

    private DiscountDetails(List<DiscountDetail> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public static DiscountDetails from(List<DiscountDetail> discountDetails) {
        return new DiscountDetails(discountDetails);
    }

    public Integer calculateDiscountedPrice() {
        return discountDetails.stream()
                .mapToInt(DiscountDetail::getDiscountAmount)
                .sum();
    }

    public List<BenefitDetailDto> toBenefitDetailDtos() {
        return discountDetails.stream()
                .map(discount -> BenefitDetailDto.of(discount.getDiscountDescription(), discount.getDiscountAmount()))
                .toList();
    }
}
