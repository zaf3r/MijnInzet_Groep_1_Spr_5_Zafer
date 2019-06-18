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
    private SortedSet<Integer> knowledgeAreaIds = new TreeSet<>();

    public SortedSet<Integer> getKnowledgeAreas() {
        return knowledgeAreaIds;
    }

    public void setKnowledgeAreas(SortedSet<Integer> knowledgeAreaIds) {
        this.knowledgeAreaIds = knowledgeAreaIds;
    }

    public void addKnowledgeArea(int knowledgeAreaId) {
        SortedSet<Integer> knowledgeAreas = getKnowledgeAreas();
        if (!knowledgeAreas.contains(knowledgeAreaId)) knowledgeAreas.add(knowledgeAreaId);
    }

    public Cohort() {}

    public Cohort(String cohortName, LocalDate startDate, LocalDate endDate) {
        this.cohortName = cohortName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCohortName() { return cohortName; }

    public void setCohortName(String cohortName) { this.cohortName = cohortName; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public void setAllCohorts(SortedSet<Cohort> allCohorts) { this.allCohorts = allCohorts; }

    public SortedSet<Cohort> getAllCohorts() { return allCohorts; }

    @Override
    public String toString() {
        return "Cohort{" +
                "cohortName='" + cohortName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
