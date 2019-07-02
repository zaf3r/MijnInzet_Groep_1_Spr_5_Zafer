package makeitwork.mijninzet.model.CourseSchedule;

import makeitwork.mijninzet.model.Availability.PartOfDay;

import java.time.LocalDate;

public class Agenda implements Comparable<Agenda>{
    //this class is for constructing general agenda-items
    //the class is for instance used in generating the overview
    //of courseSchedule. Because this class generates redunant information
    // without any referential check, each time an agenda is needed, this agenda
    //must be generated.

    private LocalDate date;
    private int dayPart;
    private String descriptionTop="";
    private String subjectMorning="";
    private String cohortMorning="";
    private String subjectAfternoon="";
    private String cohortAfternoon="";
    private String subjectNight="";
    private String cohortNight="";

    public Agenda() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDayPart() {
        return dayPart;
    }

    public void setDayPart(int dayPart) {
        this.dayPart = dayPart;
    }

    public String getDescriptionTop() {
        return descriptionTop;
    }

    public void setDescriptionTop(String descriptionTop) {
        this.descriptionTop = descriptionTop;
    }

    public String getSubjectMorning() {
        return subjectMorning;
    }

    public void setSubjectMorning(String subjectMorning) {
        this.subjectMorning = subjectMorning;
    }

    public String getCohortMorning() {
        return cohortMorning;
    }

    public void setCohortMorning(String cohortMorning) {
        this.cohortMorning = cohortMorning;
    }

    public String getSubjectAfetrnoon() {
        return subjectAfternoon;
    }

    public void setSubjectAfternoon(String subjectAfetrnoon) {
        this.subjectAfternoon = subjectAfetrnoon;
    }

    public String getCohortAfternoon() {
        return cohortAfternoon;
    }

    public void setCohortAfternoon(String cohortAfternoon) {
        this.cohortAfternoon = cohortAfternoon;
    }

    public String getSubjectNight() {
        return subjectNight;
    }

    public void setSubjectNight(String subjectNight) {
        this.subjectNight = subjectNight;
    }

    public String getCohortNight() {
        return cohortNight;
    }

    public void setCohortNight(String cohortNight) {
        this.cohortNight = cohortNight;
    }

    @Override
    public int compareTo(Agenda o) {
        return this.getDate().compareTo(o.getDate());
    }
}
