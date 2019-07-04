package makeitwork.mijninzet.model.CourseSchedule;

import java.time.LocalDate;

public class receiveBeginClosingDate {

    private LocalDate begin;
    private LocalDate closing;
    private String cohortName;

    public receiveBeginClosingDate() {
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getClosing() {
        return closing;
    }

    public void setClosing(LocalDate closing) {
        this.closing = closing;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    @Override
    public String toString() {
        return "receiveBeginClosingDate{" +
                "begin=" + begin +
                ", closing=" + closing +
                ", cohortName='" + cohortName + '\'' +
                '}';
    }
}

