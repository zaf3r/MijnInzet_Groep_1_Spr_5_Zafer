package makeitwork.mijninzet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import makeitwork.mijninzet.model.preference.Preference;
import org.hibernate.annotations.SortNatural;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "gebruiker")
public class User implements Comparable{

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

    @JsonIgnore
    @NotNull(message=COLUMN_PASSWORD+VERPLICHT)
    @Size(min=MIN_PWD, message= "minimale lengte van een password is "+MIN_PWD)
    @Column(name = COLUMN_PASSWORD)
    private String password;

    @NotNull(message=COLUMN_USERNAME+VERPLICHT)
    @Column(name = COLUMN_USERNAME)
    private String username;

    @Column(name = COLUMN_SURNAME)
    private String surname;

    @Column(name = COLUMN_PREFIX)
    private String namePrefix;

    @JsonIgnore
    @NotNull(message=COLUMN_FAMILYNAME+VERPLICHT)
    @Column(name = COLUMN_FAMILYNAME)
    private String familyName;

    @JsonIgnore
    @NotNull(message=COLUMN_EMAIL+VERPLICHT)
    @Column(name = COLUMN_EMAIL)
    private String email;

    @NotNull(message = COLUMN_ACTIVE+VERPLICHT)
    @Column(name = COLUMN_ACTIVE)
    private int active;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private Set<Preference> preferenceSet =  new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = JOINT_TABLE_NAME,
            joinColumns = @JoinColumn(name = COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = PK_COLUMN_OTHER_ENTITY))
    private List<Role> role;

    @ElementCollection
    @SortNatural
    @Column(name = "sollicitaties")
    //toegewezen taken
    private List<Task> sollicitaties;

    public List<Task> getSollicitaties() {
        return sollicitaties;
    }

    public void setSollicitaties(List<Task> sollicitaties) {
        this.sollicitaties = sollicitaties;
    }

    public void addApprovedTask(Task sollicitatie){
        List<Task> approvedTasks = getSollicitaties();
        if (!approvedTasks.contains(sollicitatie)) approvedTasks.add(sollicitatie);
    }

    @ElementCollection
    @SortNatural
    @Column(name = "vac")
    private List<Task> task;

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    //sollicitatie aan de lijst toevoegen
    public void addApplication(Task vacature) {
        List<Task> vacatures = getTask();
        if (!vacatures.contains(vacature)) vacatures.add(vacature);
    }

    //toegewezen taak aan de lijst toevoegen


//    public void removeApplication(int vacature) {
//        SortedSet<Integer> vacatures = getTask();
//        if (vacatures.contains(vacature)) vacatures.remove(vacature);
//    }



    //    @Transient
//    private List<Sollicitatie> sollicitaties = new ArrayList<>();
//

    //contracturen?
//    @Transient
//    private String roleType = roleName.getRoleName();

    public User() {}

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

    public String encryptPassword (String plain_password){
        return BCrypt.hashpw(plain_password, BCrypt.gensalt());
    }

    // todo vanaf hier voor Docent --> moet nog op een andere manier



 //  -------------------------------------------------------------------------------------------->
    // Task functionaliteiten voor Teacher
//TO DO : mag weg
//    @ElementCollection
//    @SortNatural
//    private SortedSet<String> taskIds = new TreeSet<>();
//
//    public SortedSet<String> getTasks() {
//        return taskIds;
//    }
//    // functie veranderen naar TasksID
//
//    public void setTaskIds(SortedSet<String> taskIds) {
//        this.taskIds = taskIds;
//    }
//
//    public void addTask(String taskId) {
//        SortedSet<String> tasks = getTasks();
//        if (!tasks.contains(taskId)) tasks.add(taskId);
//    }
//
//    public void removeTask(String taskId) {
//        SortedSet<String> tasks = getTasks();
//        if (tasks.contains(taskId)) tasks.remove(taskId);
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", namePrefix='" + namePrefix + '\'' +
                ", familyName='" + familyName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
