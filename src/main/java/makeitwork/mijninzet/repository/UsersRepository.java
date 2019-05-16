package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
