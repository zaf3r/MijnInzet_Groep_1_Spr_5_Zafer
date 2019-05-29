package makeitwork.mijninzet.repository;


import makeitwork.mijninzet.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends MongoRepository<Task, String>, YourCustomRepository<Task, String> {

//    Task findById(String id);
//    clashes with Crudrepo
//    Task findDocumentById(String id);

//    Task findTaskById(String id);
//
//    Task findDocByName (String naam);
}
