package christmas.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum DecemberEventBadge {
    STAR("별", amount -> amount >= 5_000 && amount < 10_000),
    TREE("트리", amount -> amount >= 10_000 && amount < 20_000),
    SANTA("산타", amount -> amount >= 20_000),
    NONE("없음", amount -> amount >= 0 && amount < 5_000);

    private final String itemName;
    private final Function<Integer, Boolean> condition;

    DecemberEventBadge(String itemName, Function<Integer, Boolean> condition) {
        this.itemName = itemName;
        this.condition = condition;
    }

    public static DecemberEventBadge from(int benefitAmount) {
        return Arrays.stream(DecemberEventBadge.values())
                .filter(decemberEventBadge -> decemberEventBadge.isConditionMatch(benefitAmount))
                .findFirst()
                .orElse(NONE);
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isConditionMatch(int amount) {
        return condition.apply(amount);
    }
}
