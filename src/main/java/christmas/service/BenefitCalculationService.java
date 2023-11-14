package christmas.service;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountDetails;
import christmas.domain.benefit.FreeGifts;
import christmas.domain.order.Order;
import christmas.service.discount.DiscountService;
import christmas.service.gift.FreeGiftService;

public class BenefitCalculationService {
    private final DiscountService discountService;
    private final FreeGiftService freeGiftService;

    public BenefitCalculationService(DiscountService discountService, FreeGiftService freeGiftService) {
        this.discountService = discountService;
        this.freeGiftService = freeGiftService;
    }

    public Benefit calculateBenefit(Order order) {
        DiscountDetails discountDetails = discountService.applyDiscount(order);
        FreeGifts freeGifts = freeGiftService.applyGiveaway(order);
        return Benefit.of(discountDetails, freeGifts);
    }
}
