package makeitwork.mijninzet.model.Unused;

import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "knowledge_area", uniqueConstraints={@UniqueConstraint(columnNames={"kennisgebied"})})
public class KnowledgeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int knowledgeAreaId;

    @Column(name = "kennisgebied")
    String knowledgeArea;

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
