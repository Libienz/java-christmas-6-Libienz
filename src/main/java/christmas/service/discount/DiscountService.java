package christmas.service.discount;

import christmas.domain.benefit.DiscountDetail;
import christmas.domain.benefit.DiscountDetails;
import christmas.domain.order.Order;
import java.util.List;

public class DiscountService {
    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountDetails calculateApplicableDiscounts(Order order) {
        List<DiscountDetail> discountDetails = discountPolicies.stream()
                .filter(discountPolicy -> discountPolicy.supports(order))
                .map(discountPolicy -> discountPolicy.applyDiscount(order))
                .toList();

        return DiscountDetails.from(discountDetails);
    }
}
