package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;

import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;

import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class CohortController {

    @Autowired
    private SubjectRepository subRepo;
    @Autowired
    private CohortRepository coRepo;
    @Autowired
    private CohortService cohortService;

    private String selectedCohort;
    private List<Subject> possibleSubjectList;
    private List<Subject> selectedSubjectList;

    @GetMapping("/cohort")
    public String CohortHandler(Model model){
        Cohort cohort = new Cohort();

        model.addAttribute("attr1", cohort);
        model.addAttribute("cohorts", getAllCohorts());
        model.addAttribute("subjects", selectedSubjectList);
        model.addAttribute("possibleSubjects", possibleSubjectList);
        return "cohort";
    }


    public List<Cohort> getAllCohorts(){
        List<Cohort> allcohorts = coRepo.findAll();
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
    public String saveCohort(@ModelAttribute("saveCohort")Cohort cohort, Model model) {
        coRepo.save(cohort);
        return "redirect:/manager/cohort";
    }

    @PostMapping("/showSubjects")
    public String showSubjects(@RequestParam("cohortName") String cohortName){
        Cohort cohort = coRepo.findByCohortName(cohortName);
        selectedCohort = cohortName;
        subjectList(cohort);
        return "redirect:/manager/cohort";
    }

    @PostMapping("/addSubjects")
    public String addSubjectHandler(@RequestParam("subjectName") int subjectId ){
        Cohort cohort = coRepo.findByCohortName(selectedCohort);
        Subject subject = subRepo.findBySubjectId(subjectId);

        cohortService.addSubject(cohort, subject);
        subjectList(cohort);

        return "redirect:/manager/cohort";
    }

    @PostMapping("/removeSubjects")
    public String removeSubjectHandler(@RequestParam("selectedSubjectList") int subjectId){
        Cohort cohort = coRepo.findByCohortName(selectedCohort);
        Subject subject = subRepo.findBySubjectId(subjectId);

        cohortService.removeSubject(cohort, subject);
        subjectList(cohort);

        return "redirect:/manager/cohort";
    }

    public void subjectList(Cohort cohort){
        selectedSubjectList = selectedSubjects(cohort);
        possibleSubjectList = possibleSubjects(cohort);
    }


}
