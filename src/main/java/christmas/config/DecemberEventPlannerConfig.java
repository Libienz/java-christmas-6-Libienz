package christmas.config;

import christmas.controller.DecemberEventPlannerController;
import christmas.service.BenefitCalculationService;
import christmas.service.discount.ChristmasDiscountPolicy;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.DiscountService;
import christmas.service.discount.SpecialDiscountPolicy;
import christmas.service.discount.WeekDayDiscountPolicy;
import christmas.service.discount.WeekendDiscountPolicy;
import christmas.service.gift.ChampagneGiveawayPolicy;
import christmas.service.gift.GiveawayPolicy;
import christmas.service.gift.GiveawayService;
import christmas.view.InputFormatValidator;
import christmas.view.InputMapper;
import christmas.view.InputSplitter;
import christmas.view.InputView;
import christmas.view.OutputMessageResolver;
import christmas.view.OutputView;
import java.util.List;

public class DecemberEventPlannerConfig {

    public InputView inputView() {
        return new InputView(inputMapper());
    }

    public InputMapper inputMapper() {
        return new InputMapper(inputFormatValidator(), inputSplitter());
    }

    public InputSplitter inputSplitter() {
        return new InputSplitter();
    }

    public InputFormatValidator inputFormatValidator() {
        return new InputFormatValidator();
    }

    public OutputView outputView() {
        return new OutputView(outputMessageResolver());
    }

    public OutputMessageResolver outputMessageResolver() {
        return new OutputMessageResolver();
    }

    public List<DiscountPolicy> appliedDiscountPolicies() {
        ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
        WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy();
        WeekendDiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();

        return List.of(christmasDiscountPolicy, specialDiscountPolicy, weekendDiscountPolicy, weekDayDiscountPolicy);
    }

    public DiscountService discountService() {
        return new DiscountService(appliedDiscountPolicies());
    }

    public List<GiveawayPolicy> appliedGiveawayPolicies() {
        ChampagneGiveawayPolicy champagneGiveawayPolicy = new ChampagneGiveawayPolicy();

        return List.of(champagneGiveawayPolicy);
    }

    public GiveawayService giveawayService() {
        return new GiveawayService(appliedGiveawayPolicies());
    }

    public BenefitCalculationService appliedBenefitCalculatorService() {
        return new BenefitCalculationService(discountService(), giveawayService());
    }

    public DecemberEventPlannerController decemberEventPlannerController() {
        return new DecemberEventPlannerController(inputView(), outputView(), appliedBenefitCalculatorService());
    }

}
