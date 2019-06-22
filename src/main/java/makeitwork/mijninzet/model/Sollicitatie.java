package makeitwork.mijninzet.model;

public class Sollicitatie {

    private TaskStatus taskStatus;

    public enum TaskStatus {
        INTERESTED {
            public boolean gesolliciteerd() {
                return true;
            }
        },
        APPROVED {
            public boolean accepted() {
                return true;
            }
        },
        DENIED {
            public boolean notAccepted () {
                return true;
            }
        }
    }

    public boolean gesolliciteerd(){
        return false;
    }

    public boolean accepted(){
        return false;
    }

    public boolean notAccepted(){
        return false;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
