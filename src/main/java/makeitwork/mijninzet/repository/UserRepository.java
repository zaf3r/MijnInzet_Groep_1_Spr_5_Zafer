package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findAllUsersByRole(String username);

    @Override
    @Transactional
    <S extends User> S save(S s);
}
