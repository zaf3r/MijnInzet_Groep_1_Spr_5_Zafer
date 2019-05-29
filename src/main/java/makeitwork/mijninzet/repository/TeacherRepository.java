package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Teacher;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<User, Integer> {

    Teacher findByUsername (String username);
}
