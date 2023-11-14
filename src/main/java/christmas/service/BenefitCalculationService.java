package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.DiscountDetails;
import christmas.domain.FreeGifts;
import christmas.domain.Order;
import christmas.service.discount.DiscountService;
import christmas.service.gift.GiveawayService;

public class BenefitCalculationService {
    private final DiscountService discountService;
    private final GiveawayService giveawayService;

    public BenefitCalculationService(DiscountService discountService, GiveawayService giveawayService) {
        this.discountService = discountService;
        this.giveawayService = giveawayService;
    }

    public Benefit calculateBenefit(Order order) {
        DiscountDetails discountDetails = discountService.applyDiscount(order);
        FreeGifts freeGifts = giveawayService.applyGiveaway(order);
        return Benefit.of(discountDetails, freeGifts);
    }
}
