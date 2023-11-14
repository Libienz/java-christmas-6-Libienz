package christmas.service.gift;

import christmas.domain.benefit.FreeGift;
import christmas.domain.benefit.FreeGifts;
import christmas.domain.order.Order;
import java.util.List;

public class FreeGiftService {
    private final List<FreeGiftPolicy> giveawayPolicies;

    public FreeGiftService(List<FreeGiftPolicy> giveawayPolicies) {
        this.giveawayPolicies = giveawayPolicies;
    }

    public FreeGifts applyGiveaway(Order order) {
        List<FreeGift> freeGifts = giveawayPolicies.stream()
                .filter(freeGiftPolicy -> freeGiftPolicy.supports(order))
                .map(freeGiftPolicy -> freeGiftPolicy.calculateApplicableFreeGift(order))
                .toList();
        return FreeGifts.from(freeGifts);
    }
}
