package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select b from Event b where b.start >= ?1 and b.finish <= ?2")
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
