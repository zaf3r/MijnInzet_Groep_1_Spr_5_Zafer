package makeitwork.mijninzet.model;

import makeitwork.mijninzet.service.CustomUserDetailsService;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SortNatural;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "gebruiker")
public class User{

//    @Transient
//    Role roleName;

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

//    @NotNull
//    @Column(name="Naam")
//    private String naam;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private Set<Preference> preferenceSet =  new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = JOINT_TABLE_NAME,
            joinColumns = @JoinColumn(name = COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = PK_COLUMN_OTHER_ENTITY))
    private List<Role> role;

    //contracturen?
//    @Transient
//    private String roleType = roleName.getRoleName();

    public User() {}

//    public User(String naam, String roleType){
//        this.naam = naam;
//        this.roleType = roleType;
//
//    }

    // CONTROLLER MET GEGEVENS EN ROL LIST?

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
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

//    public String getNaam() {
//        return naam;
//    }

//    public String getRoleType() {
//        return roleType;
//    }

    // todo vanaf hier voor Docent --> moet nog op een andere manier


    //in deze collection worden de (unieke) taskId's opgeslagen
    //op deze wijze wordt per docent bijgehouden op welke taken
    //gereageerd is. Moet ook de status bijgehouden worden, dan kan dat ook.
    @ElementCollection
    @SortNatural
//    @Column(name="task")
    private SortedSet<String> taskIds = new TreeSet<>();

    public SortedSet<String> getTasks() {
        return taskIds;
    }

    public SortedSet<String> getTaskIds() {

        return taskIds;
    }

    public void setTaskIds(SortedSet<String> taskIds) {
        this.taskIds = taskIds;
    }

    public void addTask(String taskId) {
        SortedSet<String> tasks = getTasks();
        if (!tasks.contains(taskId)) tasks.add(taskId);
    }

    public void removeTask(String taskId) {
        SortedSet<String> tasks = getTasks();
        if (tasks.contains(taskId)) tasks.remove(taskId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
