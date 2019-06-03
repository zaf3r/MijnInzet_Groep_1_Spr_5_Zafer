package makeitwork.mijninzet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


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


 //todo   private InfoBy contactPerson;


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

  //todo  public InfoBy getContactPerson() {
    //    return contactPerson;
   // }

    public String getId() {
        return id;
    }

    public void setSluitdatum(LocalDate sluitdatum) {
        this.sluitdatum = sluitdatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return uren == task.uren &&
                titel.equals(task.titel) &&
                locatie.equals(task.locatie) &&
                beschrijving.equals(task.beschrijving) &&
                startdatum.equals(task.startdatum) &&
                sluitdatum.equals(task.sluitdatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, locatie, beschrijving, startdatum, sluitdatum, uren);
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
}
