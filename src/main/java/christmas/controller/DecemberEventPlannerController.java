package christmas.controller;

import christmas.domain.DecemberEventBadge;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.FreeGifts;
import christmas.service.AppliedBenefitCalculatorService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberEventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final AppliedBenefitCalculatorService appliedBenefitCalculatorService;

    public DecemberEventPlannerController(InputView inputView, OutputView outputView,
                                          AppliedBenefitCalculatorService appliedBenefitCalculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.appliedBenefitCalculatorService = appliedBenefitCalculatorService;
    }

    public void run() {
        outputView.printGreetingMessage();

        OrderDate orderDate = inputView.readDate();
        OrderItems orderItems = inputView.readOrder();

        outputView.printEventResultPreview(orderDate.toOrderDateDto());
        outputView.printOrderItems(orderItems.toOrderItemsDto());
        outputView.printOriginalPrice(orderItems.calculateTotalPrice());

        Order order = Order.of(orderItems, orderDate);

        FreeGifts freeGifts = appliedBenefitCalculatorService.calculateGiveaway(order);
        outputView.printGiveaway(freeGifts);

        BenefitDetailsDto benefitDetailsDto = appliedBenefitCalculatorService.calculateBenefitDetails(order);
        outputView.printAppliedBenefitInformation(benefitDetailsDto);

        Integer totalBenefitAmount = appliedBenefitCalculatorService.calculateTotalBenefitAmount(benefitDetailsDto);
        outputView.printBenefitAmount(totalBenefitAmount);

        Integer benefitAppliedPrice = appliedBenefitCalculatorService.calculateBenefitAppliedPrice(order);
        outputView.printDiscountedPrice(benefitAppliedPrice);

        outputView.printEventBadge(DecemberEventBadge.from(totalBenefitAmount));
    }
}
