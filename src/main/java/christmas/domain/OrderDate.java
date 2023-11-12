package christmas.domain;

public class OrderDate {
    private static final Integer YEAR = 2023;
    private static final Integer MONTH = 12;
    private static final Integer START_DAY_OF_DECEMBER = 1;
    private static final Integer END_DAY_OF_DECEMBER = 31;
    private static final String INVALID_RANGE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final Integer dayOfMonth;

    private OrderDate(Integer dayOfMonth) {
        validate(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
    }

    public static OrderDate from(int dayOfMonth) {
        return new OrderDate(dayOfMonth);
    }

    public Boolean isInPeriod(int startDay, int endDay) {
        return startDay <= dayOfMonth && endDay >= dayOfMonth;
    }

    public Integer calculateDayOffset(int day) {
        return dayOfMonth - day;
    }

    private void validate(Integer dayOfMonth) {
        validateRange(dayOfMonth);
    }

    private void validateRange(Integer dayOfMonth) {
        if (dayOfMonth < START_DAY_OF_DECEMBER || dayOfMonth > END_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }
}
