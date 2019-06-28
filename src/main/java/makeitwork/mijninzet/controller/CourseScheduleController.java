package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.CourseSchedule;
import makeitwork.mijninzet.model.HolidaySchedule;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.model.receiveCourse;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.repository.HolidayScheduleRepository;
import makeitwork.mijninzet.service.CohortService;
import makeitwork.mijninzet.service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
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
    String nonTeachingDay(@RequestBody String requestPayload) {
//        BasicDBObject output = new BasicDBObject();
        System.out.printf("\n\n datum om mee te zoeken%\n\n",requestPayload);
//        if (service.isNonTeachingDay(requestPayload))
//            output.put("exists", true);
//        else {
//            output.put("exists", false);
//        }
//        return output.toJson();
        return "";
    }
    @PostMapping("/storeCourse")
    public @ResponseBody
    String storeCourseSchedule(@RequestBody receiveCourse requestPayload) {
        CourseSchedule course=service.storeCourse(requestPayload);
        BasicDBObject output = new BasicDBObject();
        if (course!=null)
            output.put("exists", true);
        else {
            output.put("exists", false);
        }
        return output.toJson();
    }
}