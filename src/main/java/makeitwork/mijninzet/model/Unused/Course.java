package makeitwork.mijninzet.model.Unused;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int courseId;

    private String courseName;
    private String knowledgeField;//mischien een eigen classe?
    private int cohortNumber;

    public Course() { }

    public Course(String courseName, String knowledgeField) {
        this.courseName = courseName;
        this.knowledgeField = knowledgeField;
        this.cohortNumber = cohortNumber;
    }

    //getters en setters
    public int getCourseId() { return courseId; }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getKnowledgeField() { return knowledgeField; }

    public void setKnowledgeField(String knowledgeField) { this.knowledgeField = knowledgeField; }

    public int getCohortNumber() { return cohortNumber; }

    public void setCohortNumber(int cohortNumber) { this.cohortNumber = cohortNumber; }

}
