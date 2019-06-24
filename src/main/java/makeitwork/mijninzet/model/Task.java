package makeitwork.mijninzet.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name="Vacature")
public class Task implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titel;

    private String locatie;

    private String beschrijving;

    private String beschrijvingLang;

    private String startdatum;

    private LocalDate einddatum;

    private LocalDate sluitdatum;

    private int uren;

    private TaskStatus taskStatus;

    @ManyToOne
    User user;

    public enum TaskStatus {
        OPEN, APPROVED;
    }

    public void status(TaskStatus status) {
        switch (status) {
            case OPEN:
                break;
            case APPROVED:
                break;
        }
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


    public Task() {
    }

    public Task(int id, TaskStatus taskStatus) {
        this.id = id;
        this.taskStatus = TaskStatus.OPEN;
    }

    public Task(String title, String description, int uren, String startDate) {
        this.titel = title;
        this.beschrijving = description;
        this.uren = uren;
        this.startdatum = startDate;
    }

    public Task(int id, String title, String description, int uren) {
        this.id=id;
        this.titel = title;
        this.beschrijving = description;
        this.uren = uren;
    }

    public void setTitel(String title) {
        this.titel = title;
    }

    public void setLocatie(String location) {
        this.locatie = location;
    }

    public void setBeschrijving(String description) {
        this.beschrijving = description;
    }

    public void setUren(int uren) {
        this.uren = uren;
    }

    public String getTitel() {
        return titel;
    }

    public String getLocatie() {
        return locatie;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getBeschrijvingLang() {
        return beschrijvingLang;
    }

    public String getStartdatum() {
        return startdatum;
    }

    public LocalDate getSluitdatum() {
        return sluitdatum;
    }

    public int getUren() {
        return uren;
    }

    public int getId() {
        return id;
    }

    public void setSluitdatum(LocalDate sluitdatum) {
        this.sluitdatum = sluitdatum;
    }

    public LocalDate getEinddatum() {
        return einddatum;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", titel='" + titel + '\'' +
                ", locatie='" + locatie + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", startdat=" + startdatum +
                ", sluitdate=" + sluitdatum +
                ", uren=" + uren +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
