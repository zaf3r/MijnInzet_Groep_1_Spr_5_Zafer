package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CohortRestController {

    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    CohortService cohortService;

    private String selectedCohort;

    @GetMapping("/findCohorts")
    public List<String> findCohortsHandler(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Cohort> cohortList = cohortRepository.findAllByUser(user);
        List<String> cohortNamesOfManager = new ArrayList<>();

        for (Cohort cohort: cohortList) {
            cohortNamesOfManager.add(cohort.getCohortName());
        }
        return cohortNamesOfManager;
    }

    @GetMapping("/findSubjectsOfCohorts/{cohortName}")
    public List<Subject> findSubjectsHandler(@PathVariable("cohortName") String cohortName){
        selectedCohort = cohortName;
        Cohort cohort = cohortRepository.findByCohortName(cohortName);
        return selectedSubjects(cohort);
    }

    @GetMapping("/findPossibleSubjects/{cohortName}")
    public List<Subject> possibleSubjectsHandler(@PathVariable("cohortName") String cohortName){
        Cohort cohort = cohortRepository.findByCohortName(cohortName);
        return possibleSubjects(cohort);
    }

    @PostMapping("/addSubjectToCohort")
    public void addSubjectHandler(@RequestBody String subjectName ){
    Cohort cohort = cohortRepository.findByCohortName(selectedCohort);
    Subject subject = subjectRepository.findBySubjectName(subjectName);
    cohort.addSubject(subject);
    cohortRepository.save(cohort);
}

    @PostMapping("/removeSubjectToCohort")
    public void removeSubjectHandler(@RequestBody String subjectName){
        Cohort cohort = cohortRepository.findByCohortName(selectedCohort);
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        cohort.removeSubject(subject);
        cohortRepository.save(cohort);
    }

    public List<Subject> getAllSubjects(){
        List<Subject> allSubjects = subjectRepository.findAll();
        return allSubjects;
    }

    public List<Subject> selectedSubjects(Cohort cohort){
        List<Subject> selectedSubjects = cohortService.getAllSubjects(cohort);
        return selectedSubjects;
    }

    public List<Subject> possibleSubjects(Cohort cohort){
        List<Subject> possibleSubjects = new ArrayList<>();
        List<Subject> subjects = getAllSubjects();
        for (Subject s : subjects){
            if (!selectedSubjects(cohort).contains(s)){
                possibleSubjects.add(s);
            }
        }
        return possibleSubjects;
    }
}
