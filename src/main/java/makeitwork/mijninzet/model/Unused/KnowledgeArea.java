package makeitwork.mijninzet.model.Unused;

import makeitwork.mijninzet.model.preference.Subject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "knowledge_area", uniqueConstraints={@UniqueConstraint(columnNames={"kennisgebied"})})
public class KnowledgeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int knowledgeAreaId;

    @Column(name = "kennisgebied")
    String knowledgeArea;

    @ManyToMany
    @Column (name = "Vakkenlijst", nullable = false)
    private List<Subject> subjects = new ArrayList<>();

    //constructors
    public KnowledgeArea() {}

    public KnowledgeArea(String knowledgeArea) {
        this.knowledgeArea = knowledgeArea;

    }

    //getters & setters
    public int getKnowledgeAreaId() { return knowledgeAreaId; }

    public String getKnowledgeArea() { return knowledgeArea; }
    public void setKnowledgeArea(String knowledgeArea) { this.knowledgeArea = knowledgeArea; }

//    public Set<Subject> getSubjectSet() { return subjectSet; }
//    public void setSubjectSet(Set<Subject> subjectSet) { this.subjectSet = subjectSet; }


    @Override
    public String toString() {
        return "KnowledgeArea{" +
                "knowledgeAreaId=" + knowledgeAreaId +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}





