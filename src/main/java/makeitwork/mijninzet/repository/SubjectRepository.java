package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.preference.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public List<Subject> findAllByOrderBySubjectIdAsc();

}
