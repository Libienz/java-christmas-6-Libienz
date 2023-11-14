package christmas.domain.order;

import christmas.dto.order.OrderDateDto;
import christmas.exception.date.OrderDateRangeException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDate {
    private static final Integer YEAR = 2023;
    private static final Integer MONTH = 12;
    private static final Integer START_DAY_OF_DECEMBER = 1;
    private static final Integer END_DAY_OF_DECEMBER = 31;

    private final Integer dayOfMonth;

    private OrderDate(Integer dayOfMonth) {
        validate(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
    }

    public static OrderDate from(int dayOfMonth) {
        return new OrderDate(dayOfMonth);
    }

    public OrderDateDto toOrderDateDto() {
        return OrderDateDto.from(dayOfMonth);
    }

    public Boolean isInPeriod(int startDay, int endDay) {
        return startDay <= dayOfMonth && endDay >= dayOfMonth;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = LocalDate.of(YEAR, MONTH, dayOfMonth).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public Integer calculateDayOffset(int day) {
        return dayOfMonth - day;
    }

    public boolean isSameDate(int dayOfMonth) {
        return this.dayOfMonth == dayOfMonth;
    }

    private void validate(Integer dayOfMonth) {
        validateRange(dayOfMonth);
    }

    private void validateRange(Integer dayOfMonth) {
        if (dayOfMonth < START_DAY_OF_DECEMBER || dayOfMonth > END_DAY_OF_DECEMBER) {
            throw new OrderDateRangeException();
        }
    }
}
