package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class CohortService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transient
    private final String COORDINATOR = "Manager";
    @Transient
    private final String TEACHER = "Docent";


    public Subject getSubject(String subjectName){
        return subjectRepository.findBySubjectName(subjectName);
    }


    public List<Subject> getAllSubjects(Cohort cohort){
        List<Subject> subjects = new ArrayList<>();
        for (String subjectName: cohort.getSubjectNames()) {
            Subject subject = getSubject(subjectName);
            subjects.add(subject);
        }
        return subjects;
    }

    public void addSubject(Cohort cohort, Subject subject){
        cohort.addSubject(subject.getSubjectName());
        cohortRepository.save(cohort);
    }

    public void removeSubject(Cohort cohort, Subject subject){
        cohort.removeSubject(subject.getSubjectName());
        cohortRepository.save(cohort);
    }

    public List<User> coordinatorList(){
        List<User> coordinators = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users){
            List<Role> roles = user.getRole();
                if (roles.contains(roleRepository.findByRoleName(COORDINATOR)))
                    coordinators.add(user);
        }
        return coordinators;
    }

    public List<User> teacherList(){
        List<User> teachers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users){
            List<Role> roles = user.getRole();
            if (roles.contains(roleRepository.findByRoleName(TEACHER)))
                teachers.add(user);
        }
        return teachers;
    }

    public Cohort getCohort(String name){
        return cohortRepository.findByCohortName(name);
    }
    public List<Cohort> allCohorts(){
        return cohortRepository.findAll();
    }



}
