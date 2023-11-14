package christmas.service;

import christmas.domain.Order;
import christmas.domain.DiscountDetail;
import christmas.domain.DiscountDetails;
import java.util.List;

public class DiscountService {
    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountDetail applyDiscount(Order order) {
        List<DiscountDetails> discountDetails = discountPolicies.stream()
                .filter(discountPolicy -> discountPolicy.supports(order))
                .map(discountPolicy -> discountPolicy.applyDiscount(order))
                .toList();

        return DiscountDetail.from(discountDetails);
    }
}
