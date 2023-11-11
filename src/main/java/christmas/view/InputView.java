package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderDate;
import christmas.domain.OrderItems;

public class InputView {
    private static final String REQUEST_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final InputMapper inputMapper;

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    public OrderDate readDate() {
        System.out.println(REQUEST_DATE_MESSAGE);
        return inputMapper.mapToOrderDate(Console.readLine());
    }

    public OrderItems readOrder() {
        System.out.println(REQUEST_ORDER_MESSAGE);
        return inputMapper.mapToOrderItems(Console.readLine());
    }
}
