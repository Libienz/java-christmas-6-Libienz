package christmas.view;

import java.util.Arrays;
import java.util.List;

public class InputSplitter {
    private static final String ORDER_ITEM_DELIMITER = ",";
    private static final String MENU_COUNT_DELIMITER = "-";
    private static final Integer MENU_INDEX = 0;
    private static final Integer COUNT_INDEX = 1;

    public List<String> splitToMenuWithCounts(String target) {
        return Arrays.stream(target.split(ORDER_ITEM_DELIMITER))
                .toList();
    }

    public String splitToMenu(String target) {
        return target.split(MENU_COUNT_DELIMITER)[MENU_INDEX];
    }

    public String splitToCount(String target) {
        return target.split(MENU_COUNT_DELIMITER)[COUNT_INDEX];
    }
}
