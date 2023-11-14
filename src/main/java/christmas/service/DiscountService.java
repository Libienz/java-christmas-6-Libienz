package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountDetail;
import christmas.dto.DiscountDetails;
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
