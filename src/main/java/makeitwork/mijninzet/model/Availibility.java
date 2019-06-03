package makeitwork.mijninzet.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import static java.util.Arrays.fill;

@Entity
public class Availibility implements Comparable<Availibility>, Serializable {

    @Id
    @Column
    private LocalDate startdate;

    @Id
    @Column
    private LocalDate enddate;

    @Transient
    private int MAX_DAYS_WEEK=Weekdays.values().length;

    @Transient
    private int MAX_PART_OF_DAYS= PartOfDay.values().length;

    @Transient
    private boolean[][] available = new boolean[MAX_DAYS_WEEK][MAX_PART_OF_DAYS];

    //no arg constructor, the availibility is set to false
    public Availibility() {
        setAvailable(available,false);
    }
    //constructor for a period (start-end) of time. Availibility is set to true.
    public Availibility(LocalDate startdate, LocalDate enddate) {
        this(startdate,enddate,true);
    }
    //constructor for a period (start-end) of time. Availibility must be set (false/true).
    public Availibility(LocalDate startdate, LocalDate enddate,boolean avail) {
        this.startdate = startdate;
        this.enddate = enddate;
        setAvailable(available,avail);
    }
    //constructor for a period (start-end) of time and certains days of the week. For these days the Availibility
    // for all the parts of the day is set true. Fot the other days the avaibility is set to false.
    public Availibility(LocalDate startdate, LocalDate enddate, Weekdays[] days) {
        this(startdate, enddate, false);
        //this loop iterates over all the elements of the array
        for (Weekdays day : days
        //this loop iterates over all the elements of the enum
        ) { for (PartOfDay part : PartOfDay.values()
            ) {
                setAvailable(day, part, true);
            }
        }
    }
    //constructor for a period (start-end) of time and certains part of each day. For these parts of all days of the week
    // the Availibility is set true. For the other parts the avaibility is set to false.
    public Availibility(LocalDate startdate, LocalDate enddate, PartOfDay[] parts) {
        this(startdate, enddate, false);
        //this loop iterates over all the elements of the array
        for (PartOfDay part : parts)
        //this loop iterates over all the elements of the enum
        { for (Weekdays day : Weekdays.values())
            {
                setAvailable(day, part, true);
            }
        }
    }
    public Availibility(LocalDate startdate,
                        LocalDate enddate,
                        Weekdays day,
                        PartOfDay partOfDay){
        this(startdate,enddate,day,partOfDay,true);

    }
    //constructor for a period (start-end) of time and a specific day and part of the day.
    public Availibility(LocalDate startdate,
                        LocalDate enddate,
                        Weekdays day,
                        PartOfDay partOfDay,
                        boolean avail){
        this.startdate = startdate;
        this.enddate = enddate;
        setAvailable(day,partOfDay,avail);
    }

    //this method splashes the value true or false over all the cells of the array
    private void setAvailable(boolean[][] available, boolean avail) {
        fill(available,avail);
        }

    // a certain cell of the array is set to false or true. Remember that an array starts with and address [0][0]
    //The ordinal of an enum starts also with [0]. In loops and addressing arrays that's very convinient.
    private void setAvailable(Weekdays day,PartOfDay part, boolean avail) {
        this.available[day.ordinal()][part.ordinal()] = avail;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    @Override
    public int compareTo(Availibility a) {
        return this.getStartdate().isAfter(a.getStartdate())?10:this.getStartdate().isBefore(a.getStartdate())?-10:0;
    }
}
