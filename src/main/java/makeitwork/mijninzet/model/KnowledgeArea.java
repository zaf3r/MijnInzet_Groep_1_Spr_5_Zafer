package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name ="kennisgebied")
public class KnowledgeArea {

    @Id
    @Column(name = "naam", unique = true)
    private String knowledgeArea;

    @Transient
    private Subject vak;

    @OneToMany
    @ElementCollection
    private Set<Subject> subjects = new HashSet<>();

    //constructors
    public KnowledgeArea() {}

    //getters & setters
    public String getKnowledgeArea() { return knowledgeArea; }
    public void setKnowledgeArea(String knowledgeArea) { this.knowledgeArea = knowledgeArea; }

    public Subject getVak() {
        return vak;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

//    public void addSubject(int subjectId){
//        Set <Subject> subjects = getSubjects();
//        if (!subjects.contains(subjectId)) subjects.add(subjectId);
//    }

}
