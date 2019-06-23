package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.service.CohortService;
import makeitwork.mijninzet.service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseScheduleController {

    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    CourseScheduleRepository courseScheduleRepository;
    @Autowired
    CourseScheduleService service;
    @Autowired
    CohortService cohortService;

    @PostMapping("/cohortsToPlan")
    public @ResponseBody
    String cohortsToPlan(@RequestBody String requestPayload){
        return new Gson().toJson(service.cohortsToPlan());
    }
    @PostMapping("/vakkenCohort")
    public @ResponseBody
    String vakkenVanCohort(@RequestBody String requestPayload){
        List<Subject> subjects=service.vakkenCohort(new Gson().fromJson(requestPayload, Cohort.class));
        return new Gson().toJson(subjects);
    }
    @PostMapping("/schedulesToCopy")
    public @ResponseBody
    String schedulesToCopy(@RequestBody String requestPayload){
        return new Gson().toJson(service.plannedCohorts());
    }
}
