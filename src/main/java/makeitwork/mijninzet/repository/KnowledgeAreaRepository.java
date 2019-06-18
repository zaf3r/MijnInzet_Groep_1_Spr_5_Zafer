package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.SortedSet;

@Repository
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, String> {
    List<KnowledgeArea> findAllByOrderByKnowledgeAreaIdAsc();
    SortedSet<KnowledgeArea> findByCohort(Cohort cohort);

}
