package christmas.config;

import christmas.controller.DecemberEventPlannerController;
import christmas.service.AppliedBenefitCalculatorService;
import christmas.service.ChampagneGiveawayPolicy;
import christmas.service.ChristmasDiscountPolicy;
import christmas.service.DiscountPolicy;
import christmas.service.DiscountService;
import christmas.service.GiveawayPolicy;
import christmas.service.GiveawayService;
import christmas.service.SpecialDiscountPolicy;
import christmas.service.WeekDayDiscountPolicy;
import christmas.service.WeekendDiscountPolicy;
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

    public AppliedBenefitCalculatorService appliedBenefitCalculatorService() {
        return new AppliedBenefitCalculatorService();
    }

    public DecemberEventPlannerController decemberEventPlannerController() {
        return new DecemberEventPlannerController(inputView(), outputView(), discountService(), giveawayService(),
                appliedBenefitCalculatorService());
    }
}
