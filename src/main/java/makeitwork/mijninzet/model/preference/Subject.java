package makeitwork.mijninzet.model.preference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vak", uniqueConstraints={@UniqueConstraint(columnNames={"naamvak"})})
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codevak")
    int subjectId;

    @Column(name = "naamvak")
    String subjectName;

    @Column(nullable = true, name = "uren")
    int hours;

    @OneToMany(mappedBy = "subject", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Preference> preferenceSet = new HashSet<>();

    @Transient
    private SortedSet<Subject> allSubjects = new TreeSet<>();

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }

    public Set<Preference> getPreferenceSet() {
        return preferenceSet;
    }

    public void setPreferenceSet(Set<Preference> preferenceSet) {
        this.preferenceSet = preferenceSet;
    }

    public SortedSet<Subject> getAllSubjects() {
        return allSubjects;
    }
}
