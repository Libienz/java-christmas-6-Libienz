package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;
import christmas.service.BenefitCalculationService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberEventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BenefitCalculationService benefitCalculationService;

    public DecemberEventPlannerController(InputView inputView, OutputView outputView,
                                          BenefitCalculationService benefitCalculationService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefitCalculationService = benefitCalculationService;
    }

    public void run() {
        outputView.printGreetingMessage();

        OrderDate orderDate = inputView.readDate();
        OrderItems orderItems = inputView.readOrder();

        outputView.printEventResultPreview(orderDate.toOrderDateDto());
        outputView.printOrderItems(orderItems.toOrderItemsDto());
        outputView.printOriginalPrice(orderItems.calculateTotalPrice());

        Order order = Order.of(orderItems, orderDate);
        Benefit benefit = benefitCalculationService.calculateBenefit(order);

        outputView.printGiveaway(benefit.toFreeGiftsDto());
        outputView.printAppliedBenefitInformation(benefit.toBenefitDetailsDto());
        Integer benefitAmount = benefit.calculateTotalBenefitAmount();
        outputView.printBenefitAmount(benefitAmount);
        outputView.printDiscountedPrice(benefit.calculateBenefitAppliedPrice(order.calculatePrice()));

        outputView.printEventBadge(benefit.calculateAchievableBadge(benefitAmount));
    }
}
