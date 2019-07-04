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
    private CohortRepository cohortRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<Subject> getAllSubjects(Cohort cohort){
        List<Subject> subjects = new ArrayList<>();
        for (Subject subject: cohort.getSubjects()) {
            subjects.add(subject);
        }
        return subjects;
    }

    public List<User> userList(String roleType){
        List<User> userList = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users){
            List<Role> roles = user.getRole();
            if (roles.contains(roleRepository.findByRoleName(roleType)))
                userList.add(user);
        }
        return userList;
    }

    public Cohort getCohort(String name){
        return cohortRepository.findByCohortName(name);
    }
}
