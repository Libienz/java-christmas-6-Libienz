package christmas.service.gift;

import christmas.domain.benefit.FreeGift;
import christmas.domain.benefit.FreeGifts;
import christmas.domain.order.Order;
import java.util.List;

public class FreeGiftService {
    private final List<FreeGiftPolicy> freeGiftPolicies;

    public FreeGiftService(List<FreeGiftPolicy> freeGiftPolicies) {
        this.freeGiftPolicies = freeGiftPolicies;
    }

    public FreeGifts calculateApplicableFreeGifts(Order order) {
        List<FreeGift> freeGifts = freeGiftPolicies.stream()
                .filter(freeGiftPolicy -> freeGiftPolicy.supports(order))
                .map(freeGiftPolicy -> freeGiftPolicy.calculateApplicableFreeGift(order))
                .toList();
        return FreeGifts.from(freeGifts);
    }
}
