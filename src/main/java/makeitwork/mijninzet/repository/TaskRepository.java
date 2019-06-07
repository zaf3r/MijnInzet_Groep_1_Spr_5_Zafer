package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends MongoRepository<Task, String>, YourCustomRepository<Task, String> {

}
