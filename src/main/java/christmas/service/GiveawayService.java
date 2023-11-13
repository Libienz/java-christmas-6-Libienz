package christmas.service;

import christmas.domain.Order;
import christmas.dto.GiveawayResultDto;
import christmas.dto.GiveawayResultsDto;
import java.util.List;

public class GiveawayService {
    private final List<GiveawayPolicy> giveawayPolicies;

    public GiveawayService(List<GiveawayPolicy> giveawayPolicies) {
        this.giveawayPolicies = giveawayPolicies;
    }

    public GiveawayResultsDto applyGiveaway(Order order) {
        List<GiveawayResultDto> giveawayResultDtos = giveawayPolicies.stream()
                .filter(giveawayPolicy -> giveawayPolicy.supports(order))
                .map(giveawayPolicy -> giveawayPolicy.applyGiveaway(order))
                .toList();
        return GiveawayResultsDto.from(giveawayResultDtos);
    }
}
