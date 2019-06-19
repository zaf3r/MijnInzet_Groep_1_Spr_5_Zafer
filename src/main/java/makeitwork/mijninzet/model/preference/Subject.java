package makeitwork.mijninzet.model.preference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.KnowledgeArea;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vak")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codevak", nullable = false)
    int subjectId;

    @Column(name = "naamvak")
    String subjectName;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Preference> preferenceSet = new HashSet<>();

    @Transient
    @JsonIgnore
    private List<Subject> allSubjects = new ArrayList<>();

    @ManyToMany
    private List<KnowledgeArea> knowledgeAreas;

    @Transient
    private KnowledgeArea ka;

    public Subject(){};

    public Subject(String subjectName) {
        this.subjectName = subjectName;

    }

    //getter en setters
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

    public List<KnowledgeArea> getKnowledgeAreas() {
        return knowledgeAreas;
    }

    public void setKnowledgeAreas(List<KnowledgeArea> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }

    public void addKnowledgeToSubject(int id){
        Subject newSubject = new Subject();
        KnowledgeArea knowledgeArea = new KnowledgeArea();
        newSubject.getKnowledgeAreas().add(knowledgeArea);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", preferenceSet=" + preferenceSet +
                ", allSubjects=" + allSubjects +
                ", knowledgeAreas=" + knowledgeAreas +
                '}';
    }
}
