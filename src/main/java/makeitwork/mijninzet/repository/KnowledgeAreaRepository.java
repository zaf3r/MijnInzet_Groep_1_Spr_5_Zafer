package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, String> {

}
