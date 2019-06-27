package makeitwork.mijninzet.model.Availability.Incident;

public class IncidentDTO {

    private int week;
    private int year;
    private boolean mondayMo;
    private boolean mondayAf;
    private boolean mondayEv;
    private boolean tuesdayMo;
    private boolean tuesdayAf;
    private boolean tuesdayEv;
    private boolean wednesdayMo;
    private boolean wednesdayAf;
    private boolean wednesdayEv;
    private boolean thursdayMo;
    private boolean thursdayAf;
    private boolean thursdayEv;
    private boolean fridayMo;
    private boolean fridayAf;
    private boolean fridayEv;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isMondayMo() {
        return mondayMo;
    }

    public void setMondayMo(boolean mondayMo) {
        this.mondayMo = mondayMo;
    }

    public boolean isMondayAf() {
        return mondayAf;
    }

    public void setMondayAf(boolean mondayAf) {
        this.mondayAf = mondayAf;
    }

    public boolean isMondayEv() {
        return mondayEv;
    }

    public void setMondayEv(boolean mondayEv) {
        this.mondayEv = mondayEv;
    }

    public boolean isTuesdayMo() {
        return tuesdayMo;
    }

    public void setTuesdayMo(boolean tuesdayMo) {
        this.tuesdayMo = tuesdayMo;
    }

    public boolean isTuesdayAf() {
        return tuesdayAf;
    }

    public void setTuesdayAf(boolean tuesdayAf) {
        this.tuesdayAf = tuesdayAf;
    }

    public boolean isTuesdayEv() {
        return tuesdayEv;
    }

    public void setTuesdayEv(boolean tuesdayEv) {
        this.tuesdayEv = tuesdayEv;
    }

    public boolean isWednesdayMo() {
        return wednesdayMo;
    }

    public void setWednesdayMo(boolean wednesdayMo) {
        this.wednesdayMo = wednesdayMo;
    }

    public boolean isWednesdayAf() {
        return wednesdayAf;
    }

    public void setWednesdayAf(boolean wednesdayAf) {
        this.wednesdayAf = wednesdayAf;
    }

    public boolean isWednesdayEv() {
        return wednesdayEv;
    }

    public void setWednesdayEv(boolean wednesdayEv) {
        this.wednesdayEv = wednesdayEv;
    }

    public boolean isThursdayMo() {
        return thursdayMo;
    }

    public void setThursdayMo(boolean thursdayMo) {
        this.thursdayMo = thursdayMo;
    }

    public boolean isThursdayAf() {
        return thursdayAf;
    }

    public void setThursdayAf(boolean thursdayAf) {
        this.thursdayAf = thursdayAf;
    }

    public boolean isThursdayEv() {
        return thursdayEv;
    }

    public void setThursdayEv(boolean thursdayEv) {
        this.thursdayEv = thursdayEv;
    }

    public boolean isFridayMo() {
        return fridayMo;
    }

    public void setFridayMo(boolean fridayMo) {
        this.fridayMo = fridayMo;
    }

    public boolean isFridayAf() {
        return fridayAf;
    }

    public void setFridayAf(boolean fridayAf) {
        this.fridayAf = fridayAf;
    }

    public boolean isFridayEv() {
        return fridayEv;
    }

    public void setFridayEv(boolean fridayEv) {
        this.fridayEv = fridayEv;
    }

    @Override
    public String toString() {
        return "IncidentDTO{" +
                ", week=" + week +
                ", year=" + year +
                ", mondayMo=" + mondayMo +
                ", mondayAf=" + mondayAf +
                ", mondayEv=" + mondayEv +
                ", tuesdayMo=" + tuesdayMo +
                ", tuesdayAf=" + tuesdayAf +
                ", tuesdayEv=" + tuesdayEv +
                ", wednesdayMo=" + wednesdayMo +
                ", wednesdayAf=" + wednesdayAf +
                ", wednesdayEv=" + wednesdayEv +
                ", thursdayMo=" + thursdayMo +
                ", thursdayAf=" + thursdayAf +
                ", thursdayEv=" + thursdayEv +
                ", fridayMo=" + fridayMo +
                ", fridayAf=" + fridayAf +
                ", fridayEv=" + fridayEv +
                '}';
    }
}
