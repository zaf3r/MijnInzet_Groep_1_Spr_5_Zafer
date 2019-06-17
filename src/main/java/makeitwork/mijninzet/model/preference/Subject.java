package makeitwork.mijninzet.model.preference;

import makeitwork.mijninzet.model.KnowledgeArea;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vak")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codevak")
    int subjectId;

    @Column(name = "naamvak")
    String subjectName;

    @OneToMany(mappedBy = "subject", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Preference> preferenceSet = new HashSet<>();

    @Transient
    private SortedSet<Subject> allSubjects = new TreeSet<>();

    @ElementCollection
    @SortNatural
    @Column (name = "kennisgebieden")
    private SortedSet<KnowledgeArea> listKnowledgeAreas = new TreeSet<>();

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<Preference> getPreferenceSet() {
        return preferenceSet;
    }

    public void setPreferenceSet(Set<Preference> preferenceSet) {
        this.preferenceSet = preferenceSet;
    }

    public SortedSet<Subject> getAllSubjects() {
        return allSubjects;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", preferenceSet=" + preferenceSet +
                ", allSubjects=" + allSubjects +
                '}';
    }
}
