package makeitwork.mijninzet.service;

import makeitwork.mijninzet.controller.RetrieveUserRole;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.CourseSchedule;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CourseScheduleRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class CourseScheduleService implements RetrieveUserRole {
    @Autowired
    CourseScheduleRepository courseScheduleRepository;
    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    CohortService cohortService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UserRepository userRepository;

    public void storeCourseSchedule(CourseSchedule schedule){
        courseScheduleRepository.saveAndFlush(schedule);
    }

    public void deleteCourseSchedule(Cohort cohort){
        List<CourseSchedule> schedules=courseScheduleRepository.findAllByCohort(cohort);
        for (CourseSchedule schedule:schedules) {
            courseScheduleRepository.delete(schedule);
        }
    }
    public List<CourseSchedule> courseSchedule(Cohort cohort){
        //a list of courses given a cohort
        return courseScheduleRepository.findAllByCohort(cohort);
    }
    public  List<Cohort> cohortsInSchedules(){
        //all cohorts from all Courses regardless status
        List<Cohort> cohortsPlan=new ArrayList<>(cohorts(scheduledCourses()));
        return cohortsPlan;
    }
    public List<Cohort> plannedCohorts(List<CourseSchedule> courses){
        //all cohorts with a final planning
        List<Cohort> cohortsPlan=new ArrayList<>(cohorts(finalPlannedCourses(scheduledCourses())));
        return cohortsPlan;
    }
    private List<CourseSchedule> scheduledCourses(){
        //list of all courses
        return courseScheduleRepository.findAll();
    }
    private SortedSet<Cohort> cohorts(List<CourseSchedule> schedules) {
        //a list of courses is converted into a list of unique cohorts
        SortedSet<Cohort> cohorts = new TreeSet<>();
        for (CourseSchedule course : schedules) {
            var cohort = course.getCohort();
            if (!cohorts.contains(cohort)) cohorts.add(cohort);
        }
        return cohorts;
    }
    private List<CourseSchedule> finalPlannedCourses(List<CourseSchedule> schedules){
        //all courses with status=DEFINITIEF (planning is casted in concrete)
        List<CourseSchedule> courses =new ArrayList<>();
        for (CourseSchedule course:schedules) {
            var status=course.getStatus().name();
            var checkValueStatus="DEFINITIEF";
            if (status==checkValueStatus) {
                courses.add(course);
            }
        }
        return courses;
    }
    public List<Cohort> plannedCohorts(){
        List<Cohort> plannedCohorts=new ArrayList<>(cohorts(finalPlannedCourses(scheduledCourses())));
        return listCohortEmpty(plannedCohorts);
    }
    public List<Cohort> cohortsToPlan(Principal principal){
        //a list of cohorts in need for planning from the actual user of the system
        List<Cohort> cohorts=cohortRepository.findAll();
        List<Cohort> cohorts1=new ArrayList<>();
        cohorts.removeAll(plannedCohorts());
        User actualUser = userRepository.findByUsername(principal.getName());
        if (!cohorts.isEmpty()) {
            for (Cohort cohort : cohorts) {
                if (cohort.getUser().getUsername() == actualUser.getUsername()) cohorts1.add(cohort) ;
            }
        }
        listCohortEmpty(cohorts1);
        return cohorts1;
    }
    private List<Cohort> listCohortEmpty(List<Cohort> cohorts){
        //a list with one cohort, if the original list is empty
        //the cohort in the list contains a meaningfull message for te user
        if(cohorts.isEmpty()){
            Cohort cohort=new Cohort();
            cohort.setCohortName("Helaas geen cohort in dit bestand.");
            cohort.setStartDate(LocalDate.now());
            cohort.setEndDate(LocalDate.now());
            cohorts.add(cohort);
        }
        return cohorts;
    }
    public Cohort fullCohort(Cohort cohort){
        return cohortRepository.findByCohortName(cohort.getCohortName());
    }
    public List<Subject> vakkenCohort(Cohort cohort){
        List<Subject> subjects=new ArrayList<>();
        for (String name:fullCohort(cohort).getSubjectNames()) {
            subjects.add(subjectRepository.findBySubjectName(name));
        }
        return subjects;
    }


}
