package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.preference.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.SortedSet;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByOrderBySubjectIdAsc();
    Subject findBySubjectId(int id);

}

