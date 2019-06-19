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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public HolidaySchedule(LocalDate localDate) {
        this.localDate = localDate;
    }
}
