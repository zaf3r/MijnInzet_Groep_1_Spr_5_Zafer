package makeitwork.mijninzet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;


@Document(collection="Vacature")
public class Task implements Comparable{

    @Id
    private String id;

    private String titel;

    private String locatie;

    private String beschrijving;

    private String beschrijvingLang;

    private String startdatum;

    private LocalDate einddatum;

    private LocalDate sluitdatum;

    private int uren;

    private InfoBy contactPerson;//nog naar het Nederland vertalen

    private Sollicitatie sol ;

    public Task() {
    }

    public Task(String title, String description, int uren, String startDate) {
        this.titel = title;
        this.beschrijving = description;
        this.uren = uren;
        this.startdatum = startDate;
    }
    public Task(String id, String title, String description, int uren) {
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

    public InfoBy getContactPerson() {
        return contactPerson;
    }

    public String getId() {
        return id;
    }

    public void setSluitdatum(LocalDate sluitdatum) {
        this.sluitdatum = sluitdatum;
    }

    public LocalDate getEinddatum() {
        return einddatum;
    }

    @Transient
    //List <User>
    private SortedSet<Integer> userIds = new TreeSet<>();

    public SortedSet<Integer> getUsers() {
        return userIds;
    }

    public SortedSet<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(SortedSet<Integer> userIds) {
        this.userIds = userIds;
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
