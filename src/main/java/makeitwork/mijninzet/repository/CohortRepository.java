package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {
  Cohort findByCohortName(String name);
  List<Cohort> findByUser(User user);
}
