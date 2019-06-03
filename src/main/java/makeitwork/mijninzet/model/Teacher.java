package makeitwork.mijninzet.model;


import makeitwork.mijninzet.repository.TaskRepository;
import org.hibernate.annotations.SortNatural;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name="docent")
public class Teacher extends User{

    //in deze collection worden de (unieke) taskId's opgeslagen
    //op deze wijze wordt per docent bijgehouden op welke taken
    //gereageerd is. Moet ook de status bijgehouden worden, dan kan dat ook.
    @ElementCollection
    @SortNatural
    private SortedSet<String> taskIds = new TreeSet<>();


    @ElementCollection
    @SortNatural
    private SortedSet<Availibility> avaibility = new TreeSet<>();

    public Teacher() {
    }

    public SortedSet<String> getTasks() {
        return taskIds;
    }

    public void setTaskIds(SortedSet<String> taskIds) {
        this.taskIds = taskIds;
    }
    public void addTask(String taskId){
        SortedSet<String> tasks=getTasks();
        if (!tasks.contains(taskId)) tasks.add(taskId);
    }
    public void removeTask(String taskId){
        SortedSet<String> tasks=getTasks();
        if (tasks.contains(taskId)) tasks.remove(taskId);
    }
}

