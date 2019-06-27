package makeitwork.mijninzet.model;

public class SollicitatieDTO {

    String taskTitle;

    String userName;

    Task.TaskStatus status;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Task.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Task.TaskStatus status) {
        this.status = status;
    }

    // betreffende user uit de db halen
    // vang je op in de dto
    //
}
