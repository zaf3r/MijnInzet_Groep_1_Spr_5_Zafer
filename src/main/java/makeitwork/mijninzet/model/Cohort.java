package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.preference.Subject;
import org.hibernate.annotations.SortNatural;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


@Entity
@Table(name = "cohort")
public class Cohort {

    @Transient
    private final String JOINT_TABLE_NAME = "vakken_cohort";
    @Transient
    private final String COLUMN_ID = "cohortId";
    @Transient
    private final String PK_COLUMN_OTHER_ENTITY = "codevak";

    @Transient
    private List<Cohort> allCohorts = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cohortId")
    private int cohortId;

    @Column(name = "cohortNaam")
    private String cohortName;

    @Column(name = "startDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = JOINT_TABLE_NAME,
            joinColumns = @JoinColumn(name = COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = PK_COLUMN_OTHER_ENTITY))
    private List<Subject> subject;

//    @ElementCollection
//    @SortNatural
//    private SortedSet<Integer> subjectIds = new TreeSet<>();
//
//    public SortedSet<Integer> getSubjects() {
//        return subjectIds;
//    }
//
//    public SortedSet<Integer> getSubjectIds() {
//
//        return subjectIds;
//    }
//
//    public void setSubjectIds(SortedSet<Integer> subjectIds) {
//        this.subjectIds = subjectIds;
//    }
//
//    public void addSubject(int subjectId) {
//        SortedSet<Integer> subjects = getSubjects();
//        if (!subjects.contains(subjectId)) subjects.add(subjectId);
//    }
//
//    public void removeSubject(int subjectId) {
//        SortedSet<String> tasks = getTasks();
//        if (tasks.contains(taskId)) tasks.remove(taskId);
//    }

    public Cohort() {}

    public Cohort(String cohortName, LocalDate startDate, LocalDate endDate) {
        this.cohortName = cohortName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCohortId() { return cohortId; }

    public void setCohortId(int cohortId) { this.cohortId = cohortId; }

    public String getCohortName() { return cohortName; }

    public void setCohortName(String cohortName) { this.cohortName = cohortName; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public List<Subject> getSubject() { return subject; }

    public void setSubject(List<Subject> subject) { this.subject = subject; }

    public List<Cohort> allCohorts(){ return allCohorts; }

    @Override
    public String toString() {
        return "Cohort{" +
                "cohortId=" + cohortId +
                ", cohortName='" + cohortName + '\'' +
                ", subject=" + subject +
                '}';
    }
}
