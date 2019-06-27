package makeitwork.mijninzet.model;

import makeitwork.mijninzet.model.preference.Preference;
import org.hibernate.annotations.SortNatural;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
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
    private final String COLUMN_HOURS="Uren";

    @Transient
    private final String COLUMN_ID = "idgebruiker";

    @Transient
    private final String COLUMN_EMAIL="email";

    @Transient
    private final String COLUMN_SURNAME="voornaam";

    @Transient
    private final String COLUMN_PREFIX="voorvoegsel";

    @Transient
    private final String COLUMN_FAMILYNAME="achternaam";

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

    @Column(name = COLUMN_HOURS)
    private int hours;

    @Column(name = COLUMN_SURNAME)
    private String surname;

    @Column(name = COLUMN_PREFIX)
    private String namePrefix;

    @NotNull(message=COLUMN_FAMILYNAME+VERPLICHT)
    @Column(name = COLUMN_FAMILYNAME)
    private String familyName;

    @NotNull(message=COLUMN_EMAIL+VERPLICHT)
    @Column(name = COLUMN_EMAIL)
    @Email
    private String email;

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

;

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

    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String encryptPassword (String plain_password){
        return BCrypt.hashpw(plain_password, BCrypt.gensalt());
    }

    // todo vanaf hier voor Docent --> moet nog op een andere manier


    //in deze collection worden de (unieke) taskId's opgeslagen
    //op deze wijze wordt per docent bijgehouden op welke taken
    //gereageerd is. Moet ook de status bijgehouden worden, dan kan dat ook.
    @ElementCollection
    @SortNatural
//  @Column(name="task")
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
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", hours='" + hours + '\'' +
                ", surname='" + surname + '\'' +
                ", namePrefix='" + namePrefix + '\'' +
                ", familyName='" + familyName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
