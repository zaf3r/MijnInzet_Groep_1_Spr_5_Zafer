package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class CohortController {

    @Autowired
    private SubjectRepository subRepo;
    @Autowired
    private CohortRepository coRepo;

    @GetMapping("/cohort")
    public String addCohortHandler(Model model){
        Cohort cohort = new Cohort();
        model.addAttribute("attr1", cohort);
        model.addAttribute("cohorts", getCohortList());
        model.addAttribute("subjects", getSubjectList());
        return "cohort";
    }

    public List<Subject> getSubjectList() {
        List<Subject> allSubjects = subRepo.findAllByOrderBySubjectIdAsc();
        return allSubjects;
    }

    public List<Cohort> getCohortList(){
        List<Cohort> allCohorts = coRepo.findAllByOrderByCohortIdAsc();
        return allCohorts;
    }

    @PostMapping("/saveCohort")
    public String saveCohort(@ModelAttribute("saveCohort")Cohort cohort, Model model) {

        model.addAttribute("cohortName", cohort.getCohortName());
        model.addAttribute("startDate", cohort.getStartDate());
        model.addAttribute("endDate", cohort.getEndDate());


        coRepo.save(cohort);
        return "redirect:/manager/cohort";
    }

}
