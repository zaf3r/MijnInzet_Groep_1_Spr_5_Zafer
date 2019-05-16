package makeitwork.mijninzet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="docentt")
public class Teacher {


    @Id
    @Column(name="idGebruiker")
    private int id;

    @Transient
    private List<Teacher> teachers;

    //Hybernate mapping of the tables 2× @ManyToOne with IncidentalAvalability & GlobalAvalability
    @ManyToOne
    @JoinColumn(name="dayParts")
    @JoinColumn(name="incidenten")
    public List<Teacher> getTeachers() { return teachers; }

    //no-args
    public Teacher() { }

    //all-args
    public Teacher(int id) { this.id = id; }

    //getters
    public int getId() {
        return id;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    //toString
    @Override
    public String toString() {
        return "Docent{" +
                "id=" + id +
                '}';
    }
}
