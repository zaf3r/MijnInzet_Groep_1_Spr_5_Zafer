package makeitwork.mijninzet.model;

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
    private String knowledgeField;
    private int cohortNumber;

    public Course() { super();}

    public Course(String courseName, String knowledgeField) {
        super();
        this.courseName = courseName;
        this.knowledgeField = knowledgeField;
        this.cohortNumber = cohortNumber;
    }

    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String course) { this.courseName = course; }
    public String getKnowledgeField() { return knowledgeField; }
    public void setKnowledgeField(String knowledgeField) {
        this.knowledgeField = knowledgeField;
    }

    public int getCohortNumber() { return cohortNumber; }
    public void setCohortNumber(int cohortNumber) { this.cohortNumber = cohortNumber; }
}
