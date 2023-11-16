package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderItems;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputView {
    private static final String REQUEST_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final InputMapper inputMapper;

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    public OrderDate readDate() {
        return requestUntilCorrectFormArrive(REQUEST_DATE_MESSAGE, Console::readLine, inputMapper::mapToOrderDate);
    }

    public OrderItems readOrder() {
        return requestUntilCorrectFormArrive(REQUEST_ORDER_MESSAGE, Console::readLine, inputMapper::mapToOrderItems);
    }

    private <T> T requestUntilCorrectFormArrive(String requestMessage, Supplier<String> inputSupplier,
                                                Function<String, T> mapper) {
        while (true) {
            try {
                System.out.println(requestMessage);
                return mapper.apply(inputSupplier.get().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
