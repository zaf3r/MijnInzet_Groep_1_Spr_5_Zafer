package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacatureRepository extends JpaRepository<User, Integer> {

}

