package christmas.service;

import christmas.domain.Order;
import christmas.dto.DiscountResultDto;
import christmas.dto.DiscountResultsDto;
import java.util.List;

public class DiscountService {
    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public DiscountResultsDto applyDiscount(Order order) {
        List<DiscountResultDto> discountResultDtos = discountPolicies.stream()
                .filter(discountPolicy -> discountPolicy.supports(order))
                .map(discountPolicy -> discountPolicy.applyDiscount(order))
                .toList();

        return DiscountResultsDto.from(discountResultDtos);
    }
}
