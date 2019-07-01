package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Availability.Incident.Incident;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findAllByYearAndWeeknumberAndUser(int year, int weeknumber, User user);

    List<Incident> findAllByUser(User user);

    Incident findIncidentByUserAndDateIncident(User user, LocalDate dateIncident);
}
