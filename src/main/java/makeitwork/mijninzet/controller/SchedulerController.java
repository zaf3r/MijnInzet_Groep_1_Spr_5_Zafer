package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/scheduler")
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
    private String selectedTeacher;
    //private List<User> selectedTeacherList;
    private List<Subject> selectedSubject;


    @GetMapping("/bezetting")
    public String teacherHandler(Model model){
        //maybe create a new set for each subject?
        model.addAttribute("cohorts", getAllCohorts());
        model.addAttribute("allTeachers", getTeachters());
        model.addAttribute("teachers", selectedTeacher);
        model.addAttribute("subjects", selectedSubject);
        return "vakdocent";
    }

    public List<Cohort> getAllCohorts(){
        List<Cohort> allcohorts = coRepo.findAll();
        return allcohorts;
    }

    public List<User> getTeachters(){
        List<User> allTeachers = cohortRepo.teacherList();
        return allTeachers;
    }

    public List<Subject> selectedSubjectsList(Cohort cohort){
        List<Subject> selectedSubjects = cohortRepo.getAllSubjects(cohort);
        return selectedSubjects;
    }

//    @PostMapping("/showSubjects")
//    public String showSubjects(@RequestParam("cohortName") String cohortName){
//        Cohort cohort = coRepo.findByCohortName(cohortName);
//        selectedCohort = cohortName;
//        subjectList(cohort);
//        return "redirect:/roosteraar/vakdocent";
//    }


    @PostMapping("/showSubjects")
    public String showSubjects(@RequestParam("cohortName") String cohortName, Model model){
        Cohort cohort = coRepo.findByCohortName(cohortName);
        selectedCohort = cohortName;
        model.addAttribute("cohortName", selectedCohort);
        subjectList(cohort);
        return "redirect:/roosteraar/vakdocent";
    }


//    @PostMapping("/postTeacherToSubject")
//    public String postTeachers(@RequestParam("teacherName") String teacherName){
//        User user = userRepo.findByUsername(teacherName);
//        cohort.setUser(teacherName);
//        selectedTeacher = teacherName;
//        .save()
//        return "redirect:/roosteraar/vakdocent";
//    }

    public void subjectList(Cohort cohort){
        selectedSubject = selectedSubjectsList(cohort);
    }


}
