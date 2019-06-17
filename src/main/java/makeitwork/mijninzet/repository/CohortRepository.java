package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {
    List<Cohort> findAllByOrderByCohortIdAsc();
    Cohort findByCohortId(int id);

}
