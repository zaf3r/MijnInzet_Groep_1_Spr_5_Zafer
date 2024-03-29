package makeitwork.mijninzet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.model.preference.Subject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "cohort")
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cohortId")
    private int cohortId;

    @Column(name = "cohortNaam", nullable = false)
    private String cohortName;

    @Column(name = "startDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgebruiker")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "cohort_vak", joinColumns = @JoinColumn(name = "cohortId"),
            inverseJoinColumns = @JoinColumn(name = "codevak"))
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "cohort",cascade= CascadeType.PERSIST)
    private List<CohortWeek> cohortWeekList;

    public List<Subject> getSubjects() { return subjects; }

    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    public Cohort() {}

    public Cohort(String cohortName, LocalDate startDate, LocalDate endDate) {
        this.cohortName = cohortName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCohortId() { return cohortId; }

    public void setCohortId(int cohortId) { this.cohortId = cohortId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getCohortName() { return cohortName; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void addSubject(Subject subject){
        List<Subject> subjects = getSubjects();
        if(!subjects.contains(subject))
            subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        List<Subject> subjects = getSubjects();
        if (subjects.contains(subject))
            subjects.remove(subject);
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<CohortWeek> getCohortWeekList() {
        return cohortWeekList;
    }

    public void setCohortWeekList(List<CohortWeek> cohortWeekList) {
        this.cohortWeekList = cohortWeekList;
    }

    @Override
    public String toString() {
        return "Cohort{" +
                "cohortId=" + cohortId +
                ", cohortName='" + cohortName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coordinator=" + user +
                '}';
    }
}
