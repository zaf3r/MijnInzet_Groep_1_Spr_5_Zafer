package makeitwork.mijninzet.model.Availability;

public enum PartOfDay {
    OCHTEND("ochtend"),
    MIDDAG("middag"),
    AVOND("avond");

    private final String partOfDayAsString;

    PartOfDay(String partOfDay) {
        this.partOfDayAsString = partOfDay;
    }

    @Override
    public String toString() {
        return this.partOfDayAsString;
    }
}


