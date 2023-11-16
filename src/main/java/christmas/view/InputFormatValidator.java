package christmas.view;

import christmas.exception.date.InvalidOrderDateFormatException;
import christmas.exception.orders.InvalidOrdersFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFormatValidator {
    private static final String ORDER_ITEMS_PATTERN = "([가-힣]+)-([1-9][0-9]*)(?:,([가-힣]+)-([1-9][0-9]*))*";

    public void validateOrderDateInputFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidOrderDateFormatException();
        }
    }

    public void validateOrdersInputFormat(String input) {
        Pattern pattern = Pattern.compile(ORDER_ITEMS_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidOrdersFormatException();
        }
    }
}
