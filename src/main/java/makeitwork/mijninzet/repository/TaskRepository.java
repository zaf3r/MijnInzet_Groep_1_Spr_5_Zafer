package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findTaskById(int id);

    Task findByUsers(User user);

//    Task findApprovedTaskById (int TaskId, User user);
}
