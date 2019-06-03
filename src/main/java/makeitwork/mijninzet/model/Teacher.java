package makeitwork.mijninzet.model;


import org.hibernate.annotations.SortNatural;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name="docent")
public class Teacher extends User {

    public Teacher() {
        super();
    }

    public SortedSet<String> getTaskIds() {

        return taskIds;
    }

    //in deze collection worden de (unieke) taskId's opgeslagen
    //op deze wijze wordt per docent bijgehouden op welke taken
    //gereageerd is. Moet ook de status bijgehouden worden, dan kan dat ook.
    @ElementCollection
    @SortNatural
    private SortedSet<String> taskIds = new TreeSet<>();

    public SortedSet<String> getTasks() {
        return taskIds;
    }

    public void setTaskIds(SortedSet<String> taskIds) {
        this.taskIds = taskIds;
    }

    public void addTask(String taskId) {
        SortedSet<String> tasks = getTasks();
        if (!tasks.contains(taskId)) tasks.add(taskId);
    }

    public void removeTask(String taskId) {
        SortedSet<String> tasks = getTasks();
        if (tasks.contains(taskId)) tasks.remove(taskId);
    }

    @Override
    public String toString() {
        return "Teacher{" + getId() +
                "taskIds=" + taskIds +
                '}';
    }
}

