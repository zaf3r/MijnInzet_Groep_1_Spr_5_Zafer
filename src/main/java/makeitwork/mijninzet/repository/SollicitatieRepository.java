package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Sollicitatie;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SollicitatieRepository extends JpaRepository<Sollicitatie, Integer> {

    List<Sollicitatie> findByUser (User user);
}
