package christmas.exception.date;

public class InvalidOrderDateException extends IllegalArgumentException {
    private static final String INVALID_ORDER_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public InvalidOrderDateException() {
        super(INVALID_ORDER_DATE_MESSAGE);
    }
}
