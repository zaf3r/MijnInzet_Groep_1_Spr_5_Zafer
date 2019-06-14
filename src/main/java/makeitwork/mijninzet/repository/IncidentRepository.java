package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Incident.Incident;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findAllByYearAndWeeknumberAAndUser(int year, int weeknumber, User user);
}
