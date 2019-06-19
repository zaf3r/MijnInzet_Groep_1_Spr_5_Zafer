package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.HolidaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface HolidayScheduleRepository extends JpaRepository<HolidaySchedule, LocalDate> {
}
