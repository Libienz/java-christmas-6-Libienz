package christmas.view;

import christmas.domain.MenuItem;
import christmas.domain.OrderDate;
import christmas.domain.OrderItem;
import christmas.domain.OrderItems;
import java.util.List;

public class InputMapper {
    private final InputFormatValidator inputFormatValidator;
    private final InputSplitter inputSplitter;

    public InputMapper(InputFormatValidator inputFormatValidator, InputSplitter inputSplitter) {
        this.inputFormatValidator = inputFormatValidator;
        this.inputSplitter = inputSplitter;
    }

    public OrderDate mapToOrderDate(String target) {
        inputFormatValidator.validateOrderDateInputFormat(target);
        return OrderDate.from(Integer.parseInt(target));
    }

    public OrderItems mapToOrderItems(String target) {
        inputFormatValidator.validateOrdersInputFormat(target);
        List<OrderItem> orderItems = inputSplitter.splitToMenuWithCounts(target).stream()
                .map(this::mapToOrderItem)
                .toList();
        return OrderItems.from(orderItems);
    }

    private OrderItem mapToOrderItem(String menuWithCount) {
        String menuParsed = inputSplitter.splitToMenu(menuWithCount);
        String countParsed = inputSplitter.splitToCount(menuWithCount);
        return OrderItem.of(MenuItem.from(menuParsed), Integer.parseInt(countParsed));
    }
}
