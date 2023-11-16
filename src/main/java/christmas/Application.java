package christmas;

import christmas.config.DecemberEventPlannerConfig;
import christmas.controller.DecemberEventPlannerController;

public class Application {
    public static void main(String[] args) {
        DecemberEventPlannerConfig decemberEventPlannerConfig = new DecemberEventPlannerConfig();
        DecemberEventPlannerController controller = decemberEventPlannerConfig.decemberEventPlannerController();
        controller.run();
    }
}
