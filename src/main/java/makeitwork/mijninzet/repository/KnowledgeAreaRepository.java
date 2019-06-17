package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, Integer> {
    public List<KnowledgeArea> findAllByOrderByKnowledgeAreaIdAsc();
    KnowledgeArea findByKnowledgeAreaId(int id);
}
