package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Preference;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
    Set<Preference> findAllByUser(User user);
}
