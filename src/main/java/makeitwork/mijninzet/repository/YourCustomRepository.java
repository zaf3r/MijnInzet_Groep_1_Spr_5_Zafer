package makeitwork.mijninzet.repository;

public interface YourCustomRepository<Task, String> {
    Task findDocumentById(String id);
}
