package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.repository.CohortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubjectScheduleController {

    @Autowired
    CohortRepository cohortRepository;

    @PostMapping("/cohortsToPlan")
    public @ResponseBody
    String allRoles(@RequestBody String requestPayload){
        List<Cohort> cohortsPlan=new ArrayList<>();

        Gson output= new Gson();
        return output.toJson(cohortsPlan);
    }
//    private List<Cohort>



}