package makeitwork.mijninzet.model.TeacherSchedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
@Table(name = "cohortDag")
public class CohortDay {

    @Id
    @Column(name = "dagId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long dayId;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "datum")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "Dag")
    private DayOfWeek dayOfWeek;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "weekId")
    private CohortWeek cohortWeek;

    @JoinColumn(name = "docentOchtend")
    @OneToOne(cascade = CascadeType.PERSIST)
    private User teacherMorning;

    @JoinColumn(name = "docentMiddag")
    @OneToOne(cascade = CascadeType.PERSIST)
    private User teacherAfternoon;

    @JoinColumn(name = "docentAvond")
    @OneToOne(cascade = CascadeType.PERSIST)
    private User teacherEvening;

    @JoinColumn(name = "vakOchtend")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Subject subjectMorning;

    @JoinColumn(name = "vakMiddag")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Subject subjectAfternoon;

    @JoinColumn(name = "vakAvond")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Subject subjectEvening;

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

    public User getTeacherMorning() {
        return teacherMorning;
    }

    public void setTeacherMorning(User teacherMorning) {
        this.teacherMorning = teacherMorning;
    }

    public User getTeacherAfternoon() {
        return teacherAfternoon;
    }

    public void setTeacherAfternoon(User teacherAfternoon) {
        this.teacherAfternoon = teacherAfternoon;
    }

    public User getTeacherEvening() {
        return teacherEvening;
    }

    public void setTeacherEvening(User teacherEvening) {
        this.teacherEvening = teacherEvening;
    }

    public Subject getSubjectMorning() {
        return subjectMorning;
    }

    public void setSubjectMorning(Subject subjectMorning) {
        this.subjectMorning = subjectMorning;
    }

    public Subject getSubjectAfternoon() {
        return subjectAfternoon;
    }

    public void setSubjectAfternoon(Subject subjectAfternoon) {
        this.subjectAfternoon = subjectAfternoon;
    }

    public Subject getSubjectEvening() {
        return subjectEvening;
    }

    public void setSubjectEvening(Subject subjectEvening) {
        this.subjectEvening = subjectEvening;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
