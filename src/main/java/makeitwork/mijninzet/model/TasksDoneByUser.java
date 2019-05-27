package makeitwork.mijninzet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class TasksDoneByUser {
//    todo repository voor TasksDoneByUser maken
//    todo Hibernatemapping TasksDone~ByUser afmaken
//    @Column
    private Task task;

//    @Column
//    @Id
    private User user;

//    @Column
    private StatusTask status;


    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
