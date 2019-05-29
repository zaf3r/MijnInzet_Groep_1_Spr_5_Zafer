package makeitwork.mijninzet.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="docentt")
public class Teacher {


    @Transient
    private final String COLUMN_ID = "idgebruiker";

    @Transient
    private final String PK_COLUMN_TASK = "task_id";

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

//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="tasks_teacher", joinColumns = @JoinColumn(name=COLUMN_ID),
//            inverseJoinColumns = @JoinColumn(name=PK_COLUMN_TASK))
//    private List<Task> tasks;
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

    //toString
    @Override
    public String toString() {
        return "Docent{" +
                "id=" + id +
                '}';
    }
}
