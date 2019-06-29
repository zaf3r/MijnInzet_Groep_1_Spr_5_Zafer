package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule,Integer> {

    List<CourseSchedule>  findAllByCohort(Cohort cohort);
    List<CourseSchedule> findAllByDate(LocalDate date);




}
