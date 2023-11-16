package christmas.dto.benefit;

public class FreeGiftDto {
    private final String menuName;
    private final Integer count;

    private FreeGiftDto(String menuName, Integer count) {
        this.menuName = menuName;
        this.count = count;
    }

    public static FreeGiftDto of(String menuName, Integer count) {
        return new FreeGiftDto(menuName, count);
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getCount() {
        return count;
    }
}
