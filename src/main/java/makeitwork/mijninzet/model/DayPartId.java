package makeitwork.mijninzet.model;

import java.io.Serializable;

public class DayPartId implements Serializable {

    private int workday;
    private int daypart;

    public DayPartId() {}

    public DayPartId(int workday, int daypart) {
        this.workday = workday;
        this.daypart = daypart;
    }

    public int getWorkday() {
        return workday;
    }

    public int getDaypart() {
        return daypart;
    }

    public void setWorkday(int workday) {
        this.workday = workday;
    }

    public void setDaypart(int daypart) {
        this.daypart = daypart;
    }

    @Override
    public String toString() {
        return "DayPartId{" +
                "workday=" + workday +
                ", daypart=" + daypart +
                '}';
    }
}
