package makeitwork.mijninzet.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YourCustomRepository<Task, String> {
    Task findDocumentById(String id);

    List<Task> findListDocumentById(List<Task> task);
}
