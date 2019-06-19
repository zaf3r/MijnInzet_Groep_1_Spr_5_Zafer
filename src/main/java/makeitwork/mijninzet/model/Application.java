package makeitwork.mijninzet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Application {
//    todo repository voor TasksDoneByUser maken
//    todo Hibernatemapping TasksDone~ByUser afmaken
//    @Column
    private Task task;

    private User user;

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
