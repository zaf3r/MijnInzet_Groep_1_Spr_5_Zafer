package makeitwork.mijninzet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name="Vacature")
public class Task implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titel;

    private String locatie;

    private String beschrijving;

    private String beschrijvingLang;

    @JsonIgnore
    private String startdatum;
    @JsonIgnore
    private LocalDate einddatum;
    @JsonIgnore
    private LocalDate sluitdatum;

    private int uren;

    private TaskStatus taskStatus;

    @ManyToOne
    private User uitvoerder;

    public User getUitvoerder() {
        return uitvoerder;
    }

    public void setUitvoerder(User uitvoerder) {
        this.uitvoerder = uitvoerder;
    }

    @ManyToMany
    @JoinTable(name = "sollicitanten",
    joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "idgebruiker"))
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addTask(User user){
        List<User> sollicitanten = getUsers();
        if(!sollicitanten.contains(user.getId())) sollicitanten.add(user);
    }

    public enum TaskStatus {
        OPEN, APPROVED
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
        this.taskStatus = TaskStatus.OPEN;
    }

    public Task(int id, String titel) {
        this.id = id;
        this.titel = titel;
        this.taskStatus = TaskStatus.OPEN;
    }

    public Task(String title, String description, int uren, String locatie, LocalDate date, String startDate, LocalDate endDate, String longD) {
        this.titel = title;
        this.beschrijving = description;
        this.uren = uren;
        this.startdatum = startDate;
        this.locatie = locatie;
        this.sluitdatum = date;
        this.einddatum = endDate;
        this.beschrijvingLang = longD;
        this.taskStatus = TaskStatus.OPEN;
    }

    public Task(String titel, String description, int hours, String location, String longD) {
        this.id=id;
        this.titel = titel;
        this.beschrijving = description;
        this.uren = hours;
        this.locatie = location;
        this.beschrijvingLang = longD;
        this.taskStatus = TaskStatus.OPEN;
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
                "id=" + id +
                ", titel='" + titel + '\'' +
                ", locatie='" + locatie + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", beschrijvingLang='" + beschrijvingLang + '\'' +
                ", startdatum='" + startdatum + '\'' +
                ", einddatum=" + einddatum +
                ", sluitdatum=" + sluitdatum +
                ", uren=" + uren +
                ", taskStatus=" + taskStatus +
                " /n/n" +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
