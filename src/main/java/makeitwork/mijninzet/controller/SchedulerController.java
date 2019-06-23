package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SchedulerController {


    @Autowired
    private SubjectRepository subRepo;
    @Autowired
    private CohortRepository coRepo;
    @Autowired
    private CohortService cohortRepo;
    @Autowired
    private UserRepository userRepo;

    private String selectedCohort;
    private List<User> possibleTeachterList;
    private List<Subject> selectedSubjectList;

    @GetMapping("/roosteraar")
    public String roosterHandler(Model model){

        model.addAttribute("cohorts", getAllCohorts());
        model.addAttribute("teachers", posssibleTeacherList);
        model.addAttribute("subjects", selectedSubjectList);
        return "scheduler";
    }

    public List<Cohort> getAllCohorts(){
        List<Cohort> allcohorts = coRepo.findAll();
        return allcohorts;
    }

    public List<User> possibleTeacherList(User user){
        List<User> possibleTeacherList = userRepo.getAllUsersByRole(user);

        return possibleTeacherList;
    }

    public List<Subject> selectedSubjects(Cohort cohort){
        List<Subject> selectedSubjects = cohortRepo.getAllSubjects(cohort);
        return selectedSubjects;
    }

    @PostMapping("/showSubjects")
    public String showSubjects(@RequestParam("cohortName") String cohortName){
        Cohort cohort = coRepo.findByCohortName(cohortName);
        selectedCohort = cohortName;
        subjectList(cohort);
        return "redirect:/roosteraar";
    }
}
