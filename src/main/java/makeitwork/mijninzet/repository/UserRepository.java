package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    Set<User> findAllByRole(Role role);

    @Override
    @Transactional
    <S extends User> S save(S s);
}
