package makeitwork.mijninzet.model;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Sollicitatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    User user;

    @Column
    private TaskStatus taskStatus;

    @Column
    private String taskId;

    public Sollicitatie(){}

    public Sollicitatie(User user, String taskId) {
        this.user = user;
        this.taskStatus = TaskStatus.OPEN;
        this.taskId = taskId;
    }

    public enum TaskStatus {
        OPEN, APPROVED;
    }

    public void status(TaskStatus status) {
        switch (status) {
            case OPEN:
                break;
            case APPROVED:
                break;
        }
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

//    public String getStatus (TaskStatus status){
//        if (taskStatus == TaskStatus.OPEN) {
//            return "In behandeling";
//        } else
//        if (taskStatus == TaskStatus.APPROVED)
//            return "Goedgekeurd";
//        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Transient
    private SortedSet<String> taskIds = new TreeSet<>();

    public SortedSet<String> getTasks() {
        return taskIds;
    }

    public SortedSet<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(SortedSet<String> taskIds) {
        this.taskIds = taskIds;
    }

    public void addTask(String taskId) {
        SortedSet<String> tasks  = getTasks();
        if (!tasks.contains(taskId)) tasks.add(taskId);
    }

    @Override
    public String toString() {
        return "Sollicitatie{" +
                "id=" + id +
                ", user=" + user +
                ", taskStatus=" + taskStatus +
                ", taskId='" + taskId + '\'' +
                ", taskIds=" + taskIds +
                '}';
    }
}

