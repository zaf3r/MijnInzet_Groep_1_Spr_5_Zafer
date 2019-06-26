package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CohortWeekRepository extends JpaRepository<CohortWeek,Long> {
    List<CohortWeek> findCohortWeekByCohort(Cohort cohort);

    CohortWeek findCohortWeekByCohortAndWeekNumber(Cohort cohort, int weekNumber);
}
