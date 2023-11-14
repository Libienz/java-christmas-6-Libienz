package christmas.controller;

import christmas.domain.DecemberEventBadge;
import christmas.domain.FreeGifts;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;
import christmas.dto.BenefitDetailsDto;
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

        FreeGifts freeGifts = benefitCalculationService.calculateGiveaway(order);
        outputView.printGiveaway(freeGifts);

        BenefitDetailsDto benefitDetailsDto = benefitCalculationService.calculateBenefitDetails(order);
        outputView.printAppliedBenefitInformation(benefitDetailsDto);

        Integer totalBenefitAmount = benefitCalculationService.calculateTotalBenefitAmount(benefitDetailsDto);
        outputView.printBenefitAmount(totalBenefitAmount);

        Integer benefitAppliedPrice = benefitCalculationService.calculateBenefitAppliedPrice(order);
        outputView.printDiscountedPrice(benefitAppliedPrice);

        outputView.printEventBadge(DecemberEventBadge.from(totalBenefitAmount));
    }
}
