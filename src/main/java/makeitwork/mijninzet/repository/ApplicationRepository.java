package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<User, Integer> {
}
