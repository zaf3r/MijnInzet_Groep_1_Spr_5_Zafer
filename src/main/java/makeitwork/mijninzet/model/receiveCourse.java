package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.Availability.PartOfDay;

import javax.persistence.Entity;
import java.time.LocalDate;


public class receiveCourse {
    //a class to make life easier in receiving cousrse info from the view and
    //translating from json to the obect courseSchedelue

    private String cohortName;
    private String subjectName;
    private LocalDate date;
    private String partOfDay;
    private String status;

    public receiveCourse() {
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "receiveCourse{" +
                "cohortName='" + cohortName + '\'' +
                ", Subjectname='" + subjectName + '\'' +
                ", date=" + date +
                ", partOfDay='" + partOfDay + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
