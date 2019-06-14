package makeitwork.mijninzet.model.preference;

import makeitwork.mijninzet.model.KnowledgeArea;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vak")
@Embeddable
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codevak")
    int subjectId;

    @Column(name = "naamvak")
    String subjectName;

    @OneToMany(mappedBy = "subject", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Preference> preferenceSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "naam", nullable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private KnowledgeArea knowledgeArea;

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

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

}
