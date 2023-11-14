package christmas.controller;

import christmas.domain.benefit.Benefit;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItems;
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

        outputView.printFreeGifts(benefit.toFreeGiftsDto());
        outputView.printBenefitDetails(benefit.toBenefitDetailsDto());
        Integer benefitAmount = benefit.calculateTotalBenefitAmount();
        outputView.printBenefitAmount(benefitAmount);
        outputView.printDiscountedPrice(order.calculateDiscountedPrice(benefitAmount));

        outputView.printEventBadge(benefit.calculateAchievableBadge(benefitAmount).toEventBadgeDto());
    }
}
