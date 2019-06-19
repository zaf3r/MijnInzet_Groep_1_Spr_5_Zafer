package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Availability.GlobalAvailability.Availability;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    Set<Availability> findAllByUser(User user);
}
