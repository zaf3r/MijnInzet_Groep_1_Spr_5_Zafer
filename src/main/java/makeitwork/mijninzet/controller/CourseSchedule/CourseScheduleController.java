package makeitwork.mijninzet.controller.CourseSchedule;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.*;
import makeitwork.mijninzet.model.CourseSchedule.CourseSchedule;
import makeitwork.mijninzet.model.CourseSchedule.receiveCourse;
import makeitwork.mijninzet.model.CourseSchedule.receiveDatum;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.service.CohortService;
import makeitwork.mijninzet.service.CourseSchedule.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
    String cohortsToPlan(@RequestBody String requestPayload, Principal principal) {
        return new Gson().toJson(service.cohortsToPlan(principal));
    }

    @PostMapping("/vakkenCohort")
    public @ResponseBody
    String vakkenVanCohort(@RequestBody String requestPayload) {
        List<Subject> subjects = service.vakkenCohort(new Gson().fromJson(requestPayload, Cohort.class));
        return new Gson().toJson(subjects);
    }

    @PostMapping("/schedulesToCopy")
    public @ResponseBody
    String schedulesToCopy(@RequestBody String requestPayload) {
        return new Gson().toJson(service.plannedCohorts());
    }

    @PostMapping("/weekDays")
    public @ResponseBody
    String weekDays(@RequestBody String requestPayload) {
        return new Gson().toJson(service.daysOfWeek());
    }

    @PostMapping("/partOfDay")
    public @ResponseBody
    String partOfDay(@RequestBody String requestPayload) {
        return new Gson().toJson(service.partsOfDay());
    }

    @PostMapping("/subjectInfo")
    public @ResponseBody
    String subjectInfo(@RequestBody String requestPayload) {
        return new Gson().toJson(service.subjectInfo(requestPayload));
    }

    @PostMapping("/aantalDagen")
    public @ResponseBody
    String numberOfCourses(@RequestBody String requestPayload) {
        return new Gson().toJson(service.aantalDagen(requestPayload));
    }

    @PostMapping("/checkDate")
    public @ResponseBody
    String nonTeachingDay(@RequestBody receiveDatum requestPayload) {
        BasicDBObject output = new BasicDBObject();
        output.put("exists", service.isNonTeachingDay(requestPayload));
        return output.toJson();
    }
    @PostMapping("/storeCourse")
    public @ResponseBody
    String storeCourseSchedule(@RequestBody receiveCourse requestPayload) {
        var stored=service.storeCourse(requestPayload);
        BasicDBObject output = new BasicDBObject();
        if (stored)
            output.put("exists", true);
        else {
            output.put("exists", false);
        }
        return output.toJson();
    }
    @PostMapping("/plannedWorkshop")
    public @ResponseBody
    String plannedWorkshops(@RequestBody receiveDatum requestPayload) {
        List<CourseSchedule> courses=service.daySchedule(requestPayload);
        BasicDBObject output = new BasicDBObject();
        if (courses.isEmpty())
            output.put("exists", false);
        else {
            output.put("exists", true);
            for (CourseSchedule course:courses) {
                switch (course.getPartOfDay().ordinal()){
                    case 0: {output.put("morgen", true); break;}
                    case 1: {output.put("middag", true); break;}
                    case 2: {output.put("avond", true); break;}
                }
            }
        }
        return output.toJson();
    }

}