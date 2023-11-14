package christmas.service;

import christmas.domain.Order;
import christmas.dto.FreeGift;
import christmas.dto.FreeGifts;
import java.util.List;

public class GiveawayService {
    private final List<GiveawayPolicy> giveawayPolicies;

    public GiveawayService(List<GiveawayPolicy> giveawayPolicies) {
        this.giveawayPolicies = giveawayPolicies;
    }

    public FreeGifts applyGiveaway(Order order) {
        List<FreeGift> freeGifts = giveawayPolicies.stream()
                .filter(giveawayPolicy -> giveawayPolicy.supports(order))
                .map(giveawayPolicy -> giveawayPolicy.applyGiveaway(order))
                .toList();
        return FreeGifts.from(freeGifts);
    }
}
