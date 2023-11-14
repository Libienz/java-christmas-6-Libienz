package christmas.exception.orders;

public class InvalidOrdersException extends IllegalArgumentException {
    private static final String INVALID_ORDERS_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public InvalidOrdersException() {
        super(INVALID_ORDERS_MESSAGE);
    }
}
