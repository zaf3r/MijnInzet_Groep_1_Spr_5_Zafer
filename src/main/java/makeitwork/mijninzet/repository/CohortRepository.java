package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.SortedSet;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {
    Cohort findByCohortName(String name);

}
