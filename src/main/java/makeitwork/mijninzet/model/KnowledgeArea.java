package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class KnowledgeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int knowledgeAreaId;

    private String knowledgeArea;
//
//    @OneToMany(mappedBy = "knowledgeArea", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
//    private Set<Subject> subjectSet = new HashSet<>();

    //constructors
    public KnowledgeArea() {}

    public KnowledgeArea(String knowledgeArea, Set<Subject> subjectSet) {
        this.knowledgeArea = knowledgeArea;
//        this.subjectSet = subjectSet;
    }

    //getters & setters
    public int getKnowledgeAreaId() { return knowledgeAreaId; }

    public String getKnowledgeArea() { return knowledgeArea; }
    public void setKnowledgeArea(String knowledgeArea) { this.knowledgeArea = knowledgeArea; }

//    public Set<Subject> getSubjectSet() { return subjectSet; }
//    public void setSubjectSet(Set<Subject> subjectSet) { this.subjectSet = subjectSet; }
}
