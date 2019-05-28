package makeitwork.mijninzet.model;

import java.time.LocalDate;

public class Availibility {

    private LocalDate startdate;
    private LocalDate enddate;
    private PartOfDay partOfDay;
    private Weekdays weekDay;
    private Boolean available;

    //this design has the implication that every combination of weekday/partOfDay
    //must have an instance. This seems very inefficient to me (PP). An alternative can be
    //an class with {enddate, startdate. boolean[7][3] available} But this solution requires clever interfaces
    //(methods).
    //Will be continuud.
}
