package makeitwork.mijninzet.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class receiveDatum {
    //a class to make life easier in receiving cousrse info from the view and
    //translating from json to the obect courseSchedule

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String desc;

    public receiveDatum() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
