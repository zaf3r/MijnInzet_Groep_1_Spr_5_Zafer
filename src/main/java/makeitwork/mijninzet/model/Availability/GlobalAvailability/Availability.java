package makeitwork.mijninzet.model.Availability.GlobalAvailability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "beschikbaarheid")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idbeschikbaarheid")
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgebruiker")
    private User user;

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

    public Availability findMonday(List<Availability> availabilityList) {
        for (Availability availability : availabilityList) {
            if(availability.getWeekday()==Weekday.MONDAY) {
                return availability;
            }
        }
        return null;
    }

    public Availability findTuesday(List<Availability> availabilityList) {
        for (Availability availability : availabilityList) {
            if(availability.getWeekday()==Weekday.TUESDAY) {
                return availability;
            }
        }
        return null;
    }

    public Availability findWednesday(List<Availability> availabilityList) {
        for (Availability availability : availabilityList) {
            if(availability.getWeekday()==Weekday.WEDNESDAY) {
                return availability;
            }
        }
        return null;
    }

    public Availability findThursday(List<Availability> availabilityList) {
        for (Availability availability : availabilityList) {
            if(availability.getWeekday()==Weekday.THURSDAY) {
                return availability;
            }
        }
        return null;
    }

    public Availability findFriday(List<Availability> availabilityList) {
        for (Availability availability : availabilityList) {
            if(availability.getWeekday()==Weekday.FRIDAY) {
                return availability;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "user=" + user +
                ", weekday=" + weekday +
                ", morning=" + morning +
                ", afternoon=" + afternoon +
                ", evening=" + evening +
                '}';
    }
}
