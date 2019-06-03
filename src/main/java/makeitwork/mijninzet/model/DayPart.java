package makeitwork.mijninzet.model;



import javax.persistence.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;



@Entity
@Table(name = "dagdeel")
@IdClass (DayPartId.class)
public class DayPart {

    @Id
    @Column(name="werkdag")
    private int workday;
    @Id
    @Column(name="dagdeel")
    private int daypart;

    @Transient
    private List<DayPart> dayParts;

    //Hybernate mapping of the tables 1× @OneToMany with table GlobalAvalability
    @OneToMany(targetEntity = GlobalAvalability.class, mappedBy = "avalabilities",
            cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    public List<DayPart> getDayParts() { return dayParts; }

    @Transient
    private String dayPartValue;

//    @Transient
//    List<DayPart> getDayPartValues = Arrays.asList(
//            new DayPart("monday1", 1, 1),
//            new DayPart("monday2", 1, 2),
//            new DayPart("monday3", 1, 3),
//            new DayPart("tuesday1", 2, 1),
//            new DayPart("tuesday2", 2, 2),
//            new DayPart("tuesday3", 2, 3),
//            new DayPart("wednesday1", 3, 1),
//            new DayPart("wednesday2", 3, 2),
//            new DayPart("wednesday3", 3, 3),
//            new DayPart("thursday1", 4, 1),
//            new DayPart("thursday2", 4, 2),
//            new DayPart("thursday3", 4, 3),
//            new DayPart("friday1", 5, 1),
//            new DayPart("friday2", 5, 2),
//            new DayPart("friday3", 5, 3)
//    );

//    DayPart monday1 = new DayPart(1, 1);
//    DayPart monday2 = new DayPart(1, 2);
//    DayPart monday3 = new DayPart(1, 3);
//    DayPart tuesday1 = new DayPart(2, 1);
//    DayPart tuesday2 = new DayPart(2, 2);
//    DayPart tuesday3 = new DayPart(2, 3);
//    DayPart wednesday1 = new DayPart(3, 1);
//    DayPart wednesday2 = new DayPart(3, 2);
//    DayPart wednesday3 = new DayPart( 3, 3);
//    DayPart thursday1 = new DayPart( 4, 1);
//    DayPart thursday2 = new DayPart(4, 2);
//    DayPart thursday3 = new DayPart(4, 3);
//    DayPart friday1 = new DayPart( 5, 1);
//    DayPart friday2 = new DayPart( 5, 2);
//    DayPart friday3 = new DayPart( 5, 3);

    //no-arg
    public DayPart() { }

//    //all-arg2
//    public DayPart(int workday, int daypart, String dayPartValue) {
//        this.dayPartValue = dayPartValue;
//        this.workday = workday;
//        this.daypart = daypart;
//    }

    //all-args1
    public DayPart(int workday, int daypart) {
        this.workday = workday;
        this.daypart = daypart;
    }

    //getters
    public String getDayPartValue() { return dayPartValue; }
    public int getWorkday() { return workday; }
    public int getDaypart() { return daypart; }

    //setters
    public void setDayPartValue(String dayPartValue) { this.dayPartValue = dayPartValue; }
    public void setWorkday(int workday) { this.workday = workday; }
    public void setDaypart(int daypart) { this.daypart = daypart; }

    //toString
    @Override
    public String toString() {
        return "DayPart{" +
                "workday=" + workday +
                ", daypart=" + daypart +
                '}';
    }
}
