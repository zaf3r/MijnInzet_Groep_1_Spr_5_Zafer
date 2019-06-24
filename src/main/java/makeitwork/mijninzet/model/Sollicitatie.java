//package makeitwork.mijninzet.model;
//
//import javax.persistence.*;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//@Entity
//public class Sollicitatie {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    int id;
//
//    @ManyToOne
//    User user;
//
//    @ManyToOne
//    Task task;
//
//    @Column
//    private TaskStatus taskStatus;
//
//
//    public Sollicitatie() {
//    }
//
//    public Sollicitatie(User user, Task task) {
//        this.user = user;
//        this.taskStatus = TaskStatus.OPEN;
//        this.task = task;
//    }
//
//    public enum TaskStatus {
//        OPEN, APPROVED;
//    }
//
//    public void status(TaskStatus status) {
//        switch (status) {
//            case OPEN:
//                break;
//            case APPROVED:
//                break;
//        }
//    }
//
//    public TaskStatus getTaskStatus() {
//        return taskStatus;
//    }
//
//    public void setTaskStatus(TaskStatus taskStatus) {
//        this.taskStatus = taskStatus;
//    }
//
////    public String getStatus (TaskStatus status){
////        if (taskStatus == TaskStatus.OPEN) {
////            return "In behandeling";
////        } else
////        if (taskStatus == TaskStatus.APPROVED)
////            return "Goedgekeurd";
////        }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Task getTask() {
//        return task;
//    }
//
//    public void setTask(Task task) {
//        this.task = task;
//    }
//
//    @Override
//    public String toString() {
//        return "Sollicitatie{" +
//                "id=" + id +
//                ", user=" + user +
//                ", task=" + task +
//                ", taskStatus=" + taskStatus +
//                '}';
//    }
//
//
//    @Transient
//    private SortedSet<Integer> taskIds = new TreeSet<>();
//
//    //
//    public SortedSet<Integer> getTasks() {
//        return taskIds;
//    }
//
//    public SortedSet<Integer> getTaskIds() {
//        return taskIds;
//    }
//
//    public void setTaskIds(SortedSet<Integer> taskIds) {
//        this.taskIds = taskIds;
//    }
//
//    public void addTask(int taskId) {
//        SortedSet<Integer> tasks = getTasks();
//        if (!tasks.contains(taskId)) tasks.add(taskId);
//    }
//
//    public void removeTask(int taskId) {
//        SortedSet<Integer> tasks = getTasks();
//        if (tasks.contains(taskId)) tasks.remove(taskId);
//    }
//
//    }
//
//
//
//
//
//
