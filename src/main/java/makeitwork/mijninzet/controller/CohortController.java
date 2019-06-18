package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import javax.validation.constraints.Null;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SortedSet;

@Controller
@RequestMapping("/manager")
public class CohortController {

    @Autowired
    private SubjectRepository subRepo;
    @Autowired
    private CohortRepository coRepo;
    @Autowired
    private KnowledgeAreaRepository knRepo;

    @GetMapping("/cohort")
    public String addCohortHandler(Model model, Principal principal){
        Cohort cohort = new Cohort();
        Cohort selectedCohort = coRepo.findByCohortName(principal.getName());
        if (selectedCohort == null){

        }

        model.addAttribute("attr1", cohort);
        model.addAttribute("cohorts", getAllCohorts());
        model.addAttribute("knowledgeAreas", getKnowledgeAreaList(selectedCohort));
        model.addAttribute("subjects", getSubjectList());
        return "cohort";
    }

    public List<Subject> getSubjectList() {
        List<Subject> allSubjects = subRepo.findAllByOrderBySubjectIdAsc();
        return allSubjects;
    }

    public SortedSet<KnowledgeArea> getKnowledgeAreaList(Cohort cohort){
        SortedSet<KnowledgeArea> allKnowledgeAreas = knRepo.findByCohort(cohort);
        return allKnowledgeAreas;
    }

//    public SortedSet<Cohort> getAllCohorts(){
//        SortedSet<Cohort> allCohorts = coRepo.findAllByOrderByCohortIdAsc();
//        return allCohorts;
//    }

    public List<Cohort> getAllCohorts(){
        List<Cohort> allcohorts = coRepo.findAll();
        return allcohorts;
    }

    @PostMapping("/saveCohort")
    public String saveCohort(@ModelAttribute("saveCohort")Cohort cohort, Model model) {

        model.addAttribute("cohortName", cohort.getCohortName());
        model.addAttribute("startDate", cohort.getStartDate());
        model.addAttribute("endDate", cohort.getEndDate());


        coRepo.save(cohort);
        return "redirect:/manager/cohort";
    }

//    @PostMapping("/showKnowledgeArea")
//    public String showKnowledgeArea(@ModelAttribute("showKnowledgeArea")Cohort cohort, Model model){
//
//    }

}
