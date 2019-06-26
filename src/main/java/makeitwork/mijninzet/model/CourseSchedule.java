package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.Availability.PartOfDay;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="vakkenrooster")
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int courseId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cohortName")
    private Cohort cohort;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vak")
    private Subject subject;

    @Column(name="datum")
    private LocalDate date;

    @Column(name="lesdag")
    private Weekday weekday;

    @Column(name="wanneer_op_lesdag")
    private PartOfDay partOfDay;

    @Column(name="status")
    private StatusCourseSchedule status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="docent")
    private User user;

    public CourseSchedule() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public PartOfDay getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(PartOfDay partOfDay) {
        this.partOfDay = partOfDay;
    }

    public StatusCourseSchedule getStatus() {
        return status;
    }

    public void setStatus(StatusCourseSchedule status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSchedule that = (CourseSchedule) o;
        return courseId == that.courseId &&
                cohort.equals(that.cohort) &&
                subject.equals(that.subject) &&
                date.equals(that.date) &&
                weekday == that.weekday &&
                partOfDay == that.partOfDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, cohort, subject, date, weekday, partOfDay);
    }


}
