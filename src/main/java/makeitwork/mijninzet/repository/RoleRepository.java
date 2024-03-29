package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
