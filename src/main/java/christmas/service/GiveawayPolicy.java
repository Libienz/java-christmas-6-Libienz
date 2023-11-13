package christmas.service;

import christmas.domain.Order;
import christmas.dto.GiveawayResultDto;

public interface GiveawayPolicy {
    Boolean supports(Order order);

    GiveawayResultDto applyGiveaway(Order order);
}
