package christmas.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFormatValidator {

    private static final String INVALID_ORDER_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_ITEMS_PATTERN = "([가-힣]+)-([1-9][0-9]*)(?:,([가-힣]+)-([1-9][0-9]*))*";

    public void validateOrderDateInputFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_DATE);
        }
    }

    public void validateOrdersInputFormat(String input) {
        Pattern pattern = Pattern.compile(ORDER_ITEMS_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }
}
