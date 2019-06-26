package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;

import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class CohortController implements RetrieveUserRole {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private SubjectRepository subRepo;
    @Autowired
    private CohortRepository coRepo;
    @Autowired
    private CohortService cohortService;
    @Transient
    private final String COORDINATOR = "Manager";

    private String selectedCohort;
    private List<Subject> possibleSubjectList;
    private List<Subject> selectedSubjectList;

    @GetMapping("/cohort")
    public String CohortHandler(Model model, Principal principal){
        Cohort cohort = new Cohort();

        model.addAttribute("attr1", cohort);
        model.addAttribute("coordinators", getCoordinators());
//        model.addAttribute("cohorts", getAllCohorts());
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);
        return "cohort";
    }

    @GetMapping("/cohortSubject")
    public String AddSubjectHandler(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("cohorts", getAllCohorts(user));
        model.addAttribute("subjects", selectedSubjectList);
        model.addAttribute("possibleSubjects", possibleSubjectList);
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);

        return "cohortSubject";
    }

    public List<User> getCoordinators(){
        List<User> allCoordinators = cohortService.userList(COORDINATOR);
        return allCoordinators;
    }

    public List<Cohort> getAllCohorts(User user){
        List<Cohort> allcohorts = coRepo.findByUser(user);
        return allcohorts;
    }

    public List<Subject> getAllSubjects(){
        List<Subject> allSubjects = subRepo.findAll();
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

    @PostMapping("/saveCohort")
    public String saveCohort(@ModelAttribute("saveCohort")Cohort cohort, @RequestParam("coordinator")User co) {
        cohort.setUser(co);
        coRepo.save(cohort);
        return "redirect:/manager/cohort";
    }

    @PostMapping("/showSubjects")
    public String showSubjects(@RequestParam("cohortName") String cohortName, Model model){
        Cohort cohort = coRepo.findByCohortName(cohortName);
        selectedCohort = cohortName;
        model.addAttribute("cohortName", selectedCohort);
        subjectList(cohort);
        return "redirect:/manager/cohortSubject";
    }

    @PostMapping("/addSubjects")
//    public String addSubjectHandler(@RequestParam("subjectName") int subjectId ){
//        Cohort cohort = coRepo.findByCohortName(selectedCohort);
//        Subject subject = subRepo.findBySubjectId(subjectId);
//        cohortService.addSubject(cohort, subject);
//        subjectList(cohort);
//        return "redirect:/manager/cohortSubject";
//    }

    public String addSubjectHandler(@RequestParam("subjectName") int subjectId ){
        Cohort cohort = coRepo.findByCohortName(selectedCohort);
        Subject subject = subRepo.findBySubjectId(subjectId);
        cohort.addSubject(subject);
        coRepo.save(cohort);
        subjectList(cohort);

        return "redirect:/manager/cohortSubject";
    }

    @PostMapping("/removeSubjects")
//    public String removeSubjectHandler(@RequestParam("selectedSubjectList") int subjectId){
//        Cohort cohort = coRepo.findByCohortName(selectedCohort);
//        Subject subject = subRepo.findBySubjectId(subjectId);
//
//        cohortService.removeSubject(cohort, subject);
//        subjectList(cohort);
//
//        return "redirect:/manager/cohortSubject";
//    }

    public String removeSubjectHandler(@RequestParam("selectedSubjectList") int subjectId){
        Cohort cohort = coRepo.findByCohortName(selectedCohort);
        Subject subject = subRepo.findBySubjectId(subjectId);
        cohort.removeSubject(subject);
        coRepo.save(cohort);
        subjectList(cohort);

        return "redirect:/manager/cohortSubject";
    }

    public void subjectList(Cohort cohort){
        selectedSubjectList = selectedSubjects(cohort);
        possibleSubjectList = possibleSubjects(cohort);
    }
}
