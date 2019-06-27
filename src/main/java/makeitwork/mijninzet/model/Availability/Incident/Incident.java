package makeitwork.mijninzet.model.Availability.Incident;

import makeitwork.mijninzet.model.Availability.DayFinder;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Incident implements DayFinder<Incident,Weekday> {

    @Id
    @Column(name = "incident_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgebruiker")
    private User user;

    @Column(name = "jaar")
    private int year;

    @Column(name = "weeknummer")
    private int weeknumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "dag")
    private Weekday weekday;

    @Column(name = "ochtend")
    private boolean morning;

    @Column(name = "middag")
    private boolean afternoon;

    @Column(name = "avond")
    private boolean evening;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeeknumber() {
        return weeknumber;
    }

    public void setWeeknumber(int weeknumber) {
        this.weeknumber = weeknumber;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isEvening() {
        return evening;
    }

    public void setEvening(boolean evening) {
        this.evening = evening;
    }

    @Override
    public Incident findDay(Weekday weekday, List<Incident> dayList) {
        for (Incident incident: dayList) {
            if(incident.getWeekday()==weekday) {
                return incident;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", user=" + user +
                ", year=" + year +
                ", weeknumber=" + weeknumber +
                ", weekday=" + weekday +
                ", morning=" + morning +
                ", afternoon=" + afternoon +
                ", evening=" + evening +
                '}';
    }
}