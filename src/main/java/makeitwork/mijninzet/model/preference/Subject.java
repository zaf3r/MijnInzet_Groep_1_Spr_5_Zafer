package makeitwork.mijninzet.model.preference;

import makeitwork.mijninzet.model.KnowledgeArea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    //CourseManagement
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private KnowledgeArea area;

    @Transient
    private List<Subject> allSubjects = new ArrayList<>();

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

//    //CourseManager
//    public KnowledgeArea getArea() { return area; }
//    public void setArea(KnowledgeArea area) { this.area = area; }

    public List<Subject> getAllSubjects() {
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
