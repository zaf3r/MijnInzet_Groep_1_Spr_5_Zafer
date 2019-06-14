package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class CohortController {

    @Autowired
    private SubjectRepository subRepo;

    @GetMapping("/cohort")
    public String addCohortHandler(){
        return "cohort";
    }

}
