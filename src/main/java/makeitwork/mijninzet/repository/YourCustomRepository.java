package makeitwork.mijninzet.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface YourCustomRepository<Task, String> {
    Task findDocumentById(String id);
}
