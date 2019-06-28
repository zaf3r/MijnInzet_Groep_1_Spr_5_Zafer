package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.HolidaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayScheduleRepository extends JpaRepository<HolidaySchedule, LocalDate> {
    Boolean findByLocalDate(LocalDate localDate);

    List<HolidaySchedule> findAllByLocalDateAfter(LocalDate localDate);

    Boolean findFirstByLocalDate(LocalDate localDate);
}
