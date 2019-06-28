package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Availability.GlobalAvailability.Availability;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findAllByUser(User user);

    List<Availability> findAvailabilitiesByUser(User user);
}
