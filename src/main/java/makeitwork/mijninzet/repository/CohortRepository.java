package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {
  Cohort findByCohortName(String name);

}
