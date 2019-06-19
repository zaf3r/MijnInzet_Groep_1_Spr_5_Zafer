package makeitwork.mijninzet.model.Unused;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, Integer> {
    List<KnowledgeArea> findAllByOrderByKnowledgeAreaIdAsc();
    KnowledgeArea findByKnowledgeAreaId(int id);
}
