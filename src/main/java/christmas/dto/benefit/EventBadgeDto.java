package christmas.dto.benefit;

public class EventBadgeDto {
    private final String badgeName;

    public EventBadgeDto(String badgeName) {
        this.badgeName = badgeName;
    }

    public static EventBadgeDto from(String badgeName) {
        return new EventBadgeDto(badgeName);
    }

    public String getBadgeName() {
        return badgeName;
    }
}
