package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.HolidaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HolidayScheduleRepository extends JpaRepository<HolidaySchedule, LocalDate> {
    Boolean findByLocalDate(LocalDate localDate);

    List<HolidaySchedule> findAllByLocalDateAfter(LocalDate localDate);
}
