package makeitwork.mijninzet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.Availability.Availability;
import makeitwork.mijninzet.model.Incident.Incident;
import makeitwork.mijninzet.model.preference.Preference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "gebruiker")
public class User {


    //Validation fields
    @Transient
    private final String VERPLICHT=" is een verplicht veld";

    @Transient
    private final int MIN_PWD=8;


    //Column names of entity
    @Transient
    private final String COLUMN_PASSWORD="wachtwoord";

    @Transient
    private final String COLUMN_USERNAME="gebruikersnaam";

    @Transient
    private final String COLUMN_ID = "idgebruiker";

    @Transient
    private final String COLUMN_ACTIVE = "actief";


    //Constant variables that represent other table names/columns
    @Transient
    private final String JOINT_TABLE_NAME = "rollen_gebruiker";

    @Transient
    private final String PK_COLUMN_OTHER_ENTITY = "rol_id";


    //Fields that are mapped by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idgebruiker")
    private int id;

    @NotNull(message=COLUMN_PASSWORD+VERPLICHT)
    @Size(min=MIN_PWD, message= "minimale lengte van een password is "+MIN_PWD)
    @Column(name = COLUMN_PASSWORD)
    private String password;

    @NotNull(message=COLUMN_USERNAME+VERPLICHT)
    @Column(name = COLUMN_USERNAME)
    private String username;

    @NotNull(message = COLUMN_ACTIVE+VERPLICHT)
    @Column(name = COLUMN_ACTIVE)
    private int active;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private Set<Preference> preferenceSet =  new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private Set<Availability> availabilitySet =  new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private Set<Incident> incidentSet =  new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})

    @JoinTable(name = JOINT_TABLE_NAME,
            joinColumns = @JoinColumn(name = COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = PK_COLUMN_OTHER_ENTITY))
    private List<Role> role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Preference> getPreferenceSet() {
        return preferenceSet;
    }

    public void setPreferenceSet(Set<Preference> preferenceSet) {
        this.preferenceSet = preferenceSet;
    }

    public Set<Availability> getAvailabilitySet() {
        return availabilitySet;
    }

    public void setAvailabilitySet(Set<Availability> availabilitySet) {
        this.availabilitySet = availabilitySet;
    }

    public Set<Incident> getIncidentSet() {
        return incidentSet;
    }

    public void setIncidentSet(Set<Incident> incidentSet) {
        this.incidentSet = incidentSet;
    }
}
