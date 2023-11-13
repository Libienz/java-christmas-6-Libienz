package christmas.controller;

import christmas.domain.DecemberEventBadge;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;
import christmas.dto.DiscountResultsDto;
import christmas.dto.GiveawayResultsDto;
import christmas.service.AppliedBenefitCalculatorService;
import christmas.service.DiscountService;
import christmas.service.GiveawayService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberEventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountService discountService;
    private final GiveawayService giveawayService;
    private final AppliedBenefitCalculatorService appliedBenefitCalculatorService;

    public DecemberEventPlannerController(InputView inputView, OutputView outputView, DiscountService discountService,
                                          GiveawayService giveawayService,
                                          AppliedBenefitCalculatorService appliedBenefitCalculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.discountService = discountService;
        this.giveawayService = giveawayService;
        this.appliedBenefitCalculatorService = appliedBenefitCalculatorService;
    }

    public void run() {
        outputView.printGreetingMessage();

        OrderDate orderDate = inputView.readDate();
        OrderItems orderItems = inputView.readOrder();

        Order order = Order.of(orderItems, orderDate);
        Integer originalPrice = orderItems.calculateTotalPrice();
        outputView.printOriginalPrice(originalPrice);
        DiscountResultsDto discountResultsDto = discountService.applyDiscount(order);
        GiveawayResultsDto giveawayResultsDto = giveawayService.applyGiveaway(order);
        outputView.printGiveaway(giveawayResultsDto);

        outputView.printAppliedBenefitInformation(discountResultsDto, giveawayResultsDto);
        Integer benefitAmount = appliedBenefitCalculatorService.calculateTotalBenefitAmount(discountResultsDto,
                giveawayResultsDto);
        outputView.printBenefitAmount(benefitAmount);
        outputView.printDiscountedPrice(originalPrice - benefitAmount);
        outputView.printEventBadge(DecemberEventBadge.from(benefitAmount));
        
    }
}
