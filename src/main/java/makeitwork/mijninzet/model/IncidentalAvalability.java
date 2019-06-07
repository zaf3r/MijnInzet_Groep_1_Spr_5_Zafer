package makeitwork.mijninzet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name ="incidentele_beschikbaarheid")
public class IncidentalAvalability {

    @Id
    @Column(name="incidentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int incidentNumber;
    @Column(name="datum")
    private Date date;
    @Column(name="dagdeel")
    private int dayPart;
    @Column(name="idGebruiker")
    private int id;

    @Transient
    private List<IncidentalAvalability> incidenten;

    //Hybernate mapping of the tables 1× @OneToMany with Teacher
    @OneToMany(targetEntity = Teacher.class, mappedBy = "teachers",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<IncidentalAvalability> getIncidenten() { return incidenten; }

    //no-arg
    public IncidentalAvalability () {}

    //all-arg
    public IncidentalAvalability(int incidentNumber, Date date, int dayPart, int id) {
        this.incidentNumber = incidentNumber;
        this.date = date;
        this.dayPart = dayPart;
        this.id = id;
    }

    //Getters
    public int getId() { return id; }
    public Date getDate() { return date; }
    public int getDayPart() { return dayPart; }
    public int getIncidentNumber() { return incidentNumber; }

    //setters
    public void setId(int id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setDayPart(int dayPart) { this.dayPart = dayPart; }
    public void setIncidentNumber(int incidentNumber) { this.incidentNumber = incidentNumber; }

    //toString
    @Override
    public String toString() {
        return "IncidentalAvalability{" +
                "incidentNumber=" + incidentNumber +
                ", date=" + date +
                ", dayPart=" + dayPart +
                ", id=" + id +
                '}';
    }
}
