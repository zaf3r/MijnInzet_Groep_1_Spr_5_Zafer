package makeitwork.mijninzet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.time.LocalDate;


@Document(collection="Vacature")
public class Task {

    @Id
    private String id;

    private String titel;

    @Indexed(direction = IndexDirection.ASCENDING)

    private String locatie;


    private String beschrijving;
    private LocalDate startdatum;

    private LocalDate sluitdatum;

    private int uren;

    private InfoBy contactPerson;//nog naar het Nederland vertalen


    public Task() {
    }

    public Task(String title, String description, int uren, LocalDate startDate) {
        this.titel = title;
        this.beschrijving = description;
        this.uren = uren;
        this.startdatum = startDate;

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


    public LocalDate getStartdatum() {
        return startdatum;
    }

    public LocalDate getSluitdatum() {
        return sluitdatum;
    }

    public int getUren() {
        return uren;
    }

    public InfoBy getContactPerson() {
        return contactPerson;
    }

    public String getId() {
        return id;
    }
}
