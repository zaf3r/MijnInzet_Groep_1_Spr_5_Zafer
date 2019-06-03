package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface VacatureRepository extends JpaRepository<Teacher, Integer> {

    }

