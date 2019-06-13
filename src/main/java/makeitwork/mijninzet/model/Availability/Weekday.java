package makeitwork.mijninzet.model.Availability;

public enum Weekday {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday");

    private final String weekdayAsString;

    private Weekday(String weekday) {
        this.weekdayAsString = weekday;
    }

    @Override
    public String toString() {
        return this.weekdayAsString;
    }
}
