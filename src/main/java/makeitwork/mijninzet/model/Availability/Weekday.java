package makeitwork.mijninzet.model.Availability;

public enum Weekday {
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY");

    private final String weekdayAsString;

    private Weekday(String weekday) {
        this.weekdayAsString = weekday;
    }

    @Override
    public String toString() {
        return this.weekdayAsString;
    }
}