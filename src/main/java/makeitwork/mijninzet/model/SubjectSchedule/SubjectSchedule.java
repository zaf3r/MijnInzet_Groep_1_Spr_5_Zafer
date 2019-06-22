package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.Availability.PartOfDay;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Period;

@Entity
@Table(name = "vakkenrooster")
public class SubjectSchedule {

    @Column
    private Cohort cohort;
    @Column
    private Subject subject;
    @Column
    private Period period;
    @Column
    private Weekday weekday;
    @Column
    private PartOfDay partOfDay;

    @Column
    @OneToMany(mappedBy="vakkenrooster")
    private Subject teacher;

    public SubjectSchedule() {
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

    public Subject getTeacher() {
        return teacher;
    }

    public void setTeacher(Subject teacher) {
        this.teacher = teacher;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
