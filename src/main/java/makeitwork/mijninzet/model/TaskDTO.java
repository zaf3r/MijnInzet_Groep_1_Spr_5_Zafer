package makeitwork.mijninzet.model;

import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class TaskDTO {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    private int UserId;

    private String id;

    private String titel;

    private String locatie;

    private String beschrijving;

    private LocalDate startdatum;

    private LocalDate sluitdatum;

    private int uren;

    public TaskDTO(int userId, String id, String titel, String locatie, String beschrijving, LocalDate startdatum, int uren) {
        UserId = userId;
        this.id = id;
        this.titel = titel;
        this.locatie = locatie;
        this.beschrijving = beschrijving;
        this.startdatum = startdatum;
        this.uren = uren;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public LocalDate getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(LocalDate startdatum) {
        this.startdatum = startdatum;
    }

    public LocalDate getSluitdatum() {
        return sluitdatum;
    }

    public void setSluitdatum(LocalDate sluitdatum) {
        this.sluitdatum = sluitdatum;
    }

    public int getUren() {
        return uren;
    }

    public void setUren(int uren) {
        this.uren = uren;
    }
}
