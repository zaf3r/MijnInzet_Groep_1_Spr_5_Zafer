package makeitwork.mijninzet.model.preference;

import makeitwork.mijninzet.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "voorkeur", uniqueConstraints = {@UniqueConstraint(columnNames = {"codevak","idgebruiker"})})
public class Preference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idpref")
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codevak")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgebruiker")
    private User user;

    @NotNull
    @Column(name = "voorkeur")
    private int preference;

    public Preference(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }
}
