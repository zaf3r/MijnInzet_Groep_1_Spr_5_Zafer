package makeitwork.mijninzet.model;

public enum StatusTask {
// voor een enum is geen MySQL-table nodig en dus ook geen Hibernate-mapping
//    reden: enum elementen zijn constanten.
    INTERESTED,
    APPROVED,
    DOING,
    ENDED,
    CANCELLED
}
