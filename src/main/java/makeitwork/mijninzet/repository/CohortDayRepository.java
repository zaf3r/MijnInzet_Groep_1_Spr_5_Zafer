package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.TeacherSchedule.CohortDay;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CohortDayRepository extends JpaRepository<CohortDay, Long> {
    List<Weekday> findAllByDateAndTeacherMorning(LocalDate localDate, User user);

    List<Weekday>  findAllByDateAndTeacherAfternoon(LocalDate localDate, User user);

    List<Weekday>  findAllByDateAndTeacherEvening(LocalDate localDate, User user);
}
