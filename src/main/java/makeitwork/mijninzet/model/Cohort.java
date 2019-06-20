package makeitwork.mijninzet.model;


import org.hibernate.annotations.SortNatural;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.SortedSet;
import java.util.TreeSet;


@Entity
@Table(name = "cohort")
public class Cohort {

//    @Transient
//    private final String JOINT_TABLE_NAME = "vakken_cohort";
////    @Transient
////    private final String COLUMN_ID = "cohortId";
//    @Transient
//    private final String PK_COLUMN_OTHER_ENTITY = "codevak";

    @Transient
    private SortedSet<Cohort> allCohorts = new TreeSet<>();

    @Id
    @Column(name = "cohortNaam", nullable = false)
    private String cohortName;

    @Column(name = "startDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;

    @ElementCollection
    @SortNatural
    @Column(name = "naamvak")
    private SortedSet<String> subjectNames = new TreeSet<>();

    public SortedSet<String> getSubjectNames() { return subjectNames; }

    public void setSubjectNames(SortedSet<String> subjectNames) { this.subjectNames = subjectNames; }

    public Cohort() {}

    public Cohort(String cohortName, LocalDate startDate, LocalDate endDate) {
        this.cohortName = cohortName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCohortName() { return cohortName; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void addSubject(String subjectName) {
        SortedSet<String> subjects = getSubjectNames();
        if (!subjects.contains(subjectName)) subjects.add(subjectName);
    }

    public void removeSubject(String subjectName) {
        SortedSet<String> subjects = getSubjectNames();
        if (subjects.contains(subjectName)) subjects.remove(subjectName);
    }

    public void setAllCohorts(SortedSet<Cohort> allCohorts) {
        this.allCohorts = allCohorts;
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

    @Override
    public String toString() {
        return "Cohort{" +
                "cohortName='" + cohortName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
