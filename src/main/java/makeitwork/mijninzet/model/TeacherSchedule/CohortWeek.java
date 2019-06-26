package makeitwork.mijninzet.model.TeacherSchedule;

import makeitwork.mijninzet.model.Cohort;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cohortWeek")
public class CohortWeek {

    @Id
    @Column(name = "weekId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long weekId;

    @Column(name = "weekNumber")
    private int weekNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cohortId")
    private Cohort cohort;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cohortWeek" ,cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CohortDay> cohortDayList;

    public long getWeekId() {
        return weekId;
    }

    public void setWeekId(long weekId) {
        this.weekId = weekId;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public List<CohortDay> getCohortDayList() {
        return cohortDayList;
    }

    public void setCohortDayList(List<CohortDay> cohortDayList) {
        this.cohortDayList = cohortDayList;
    }

    @Override
    public String toString() {
        return "CohortWeek{" +
                "weekId=" + weekId +
                ", weekNumber=" + weekNumber +
                ", cohort=" + cohort +
                ", cohortDayList=" + cohortDayList +
                '}';
    }
}
