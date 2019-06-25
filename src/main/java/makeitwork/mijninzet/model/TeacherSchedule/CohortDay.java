package makeitwork.mijninzet.model.TeacherSchedule;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cohortDag")
public class CohortDay {

    @Id
    @Column(name = "dagId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long dayId;

    @Column(name = "datum")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "weekId")
    private CohortWeek cohortWeek;

    @Column(name = "docentOchtend")
    private String teacherMorning;

    @Column(name = "docentMiddag")
    private String teacherAfternoon;

    @Column(name = "docentAvond")
    private String teacherEvening;

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CohortWeek getCohortWeek() {
        return cohortWeek;
    }

    public void setCohortWeek(CohortWeek cohortWeek) {
        this.cohortWeek = cohortWeek;
    }

    public String getTeacherMorning() {
        return teacherMorning;
    }

    public void setTeacherMorning(String teacherMorning) {
        this.teacherMorning = teacherMorning;
    }

    public String getTeacherAfternoon() {
        return teacherAfternoon;
    }

    public void setTeacherAfternoon(String teacherAfternoon) {
        this.teacherAfternoon = teacherAfternoon;
    }

    public String getTeacherEvening() {
        return teacherEvening;
    }

    public void setTeacherEvening(String teacherEvening) {
        this.teacherEvening = teacherEvening;
    }
}
