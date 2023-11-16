package christmas.domain.menu;

import christmas.exception.orders.NoSuchMenuItemException;
import java.util.Arrays;

public enum MenuItem {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MenuCategory.MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuCategory.DESSERT),

    // 음료
    ZERO_COLA("제로콜라", 3_000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuCategory.BEVERAGE);

    private final String itemName;
    private final int price;
    private final MenuCategory category;

    MenuItem(String itemName, int price, MenuCategory category) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public static MenuItem from(String itemName) {
        return Arrays.stream(MenuItem.values())
                .filter(item -> item.itemName.equals(itemName))
                .findAny()
                .orElseThrow(NoSuchMenuItemException::new);
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }
}
