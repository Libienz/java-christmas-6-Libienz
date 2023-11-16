package christmas.dto.order;

public class OrderDateDto {
    private Integer dayOfMonth;

    private OrderDateDto(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public static OrderDateDto from(Integer dayOfMonth) {
        return new OrderDateDto(dayOfMonth);
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }
}
