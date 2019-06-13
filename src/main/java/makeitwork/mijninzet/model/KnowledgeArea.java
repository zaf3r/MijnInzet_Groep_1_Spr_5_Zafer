package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.preference.Subject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="kennisgebied")
public class KnowledgeArea {

    @Id
    @Column(name = "naam", unique = true)
    private String knowledgeArea;

    @OneToMany
    private Set<Subject> subjects = new HashSet<>();

    //constructors
    public KnowledgeArea() {}

    //getters & setters
    public String getKnowledgeArea() { return knowledgeArea; }
    public void setKnowledgeArea(String knowledgeArea) { this.knowledgeArea = knowledgeArea; }

}
