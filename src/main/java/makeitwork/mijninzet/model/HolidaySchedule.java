package makeitwork.mijninzet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table (name = "VakantieRooster")
public class HolidaySchedule {

    @Id
    @Column(name = "Datum")
    LocalDate localDate;

    @Column(name="omaschrijving")
    String description;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public HolidaySchedule(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "HolidaySchedule{" +
                "localDate=" + localDate +
                '}';
    }
}
