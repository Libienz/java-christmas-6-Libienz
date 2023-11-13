package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;
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

        Order order = Order.of(orderItems, orderDate);
        Integer originalPrice = orderItems.calculateTotalPrice();
        outputView.printOriginalPrice(originalPrice);

    }
}
