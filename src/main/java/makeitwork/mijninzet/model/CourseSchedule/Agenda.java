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
    private PartOfDay dayPart;
    private String descriptionTop;
    private String descriptionMiddle;
    private String descriptionLow;
    private String classOff; //reference to the class-type which acts as donor for the info
    private int id; //the PK of the implied classOff

    public Agenda() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PartOfDay getDayPart() {
        return dayPart;
    }

    public void setDayPart(PartOfDay dayPart) {
        this.dayPart = dayPart;
    }

    public String getDescriptionTop() {
        return descriptionTop;
    }

    public void setDescriptionTop(String descriptionTop) {
        this.descriptionTop = descriptionTop;
    }

    public String getDescriptionMiddle() {
        return descriptionMiddle;
    }

    public void setDescriptionMiddle(String descriptionMiddle) {
        this.descriptionMiddle = descriptionMiddle;
    }

    public String getDescriptionLow() {
        return descriptionLow;
    }

    public void setDescriptionLow(String descriptionLow) {
        this.descriptionLow = descriptionLow;
    }

    public String getClassOff() {
        return classOff;
    }

    public void setClassOff(String classOff) {
        this.classOff = classOff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Agenda o) {
        return this.getDate().compareTo(o.getDate());
    }
}
