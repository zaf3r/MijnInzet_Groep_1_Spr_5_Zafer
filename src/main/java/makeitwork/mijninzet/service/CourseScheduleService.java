package makeitwork.mijninzet.service;

import com.google.gson.Gson;
import makeitwork.mijninzet.controller.RetrieveUserRole;
import makeitwork.mijninzet.model.*;
import makeitwork.mijninzet.model.Availability.PartOfDay;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.*;
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
    @Autowired
    HolidayScheduleRepository holidays;

    static final int URENLESBLOK=4;
    static final String STATUS_PLANNING_COHORT="DEFINITIEF";

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
            if (status==STATUS_PLANNING_COHORT) {
                courses.add(course);
            }
        }
        return courses;
    }
    public List<Cohort> plannedCohorts(){
        List<Cohort> plannedCohorts=new ArrayList<>(cohorts(finalPlannedCourses(scheduledCourses())));
        return plannedCohorts;
    }
    public List<Cohort> cohortsToPlan(Principal principal){
        //a list of cohorts in need for planning from the actual user of the system
        List<Cohort> cohorts=cohortRepository.findAll();
        List<Cohort> cohorts1=new ArrayList<>();
        if(!plannedCohorts().isEmpty()) cohorts.removeAll(plannedCohorts());
        User actualUser = userRepository.findByUsername(principal.getName());
        if (!cohorts.isEmpty()) {
            for (Cohort cohort : cohorts) {
                if (cohort.getUser().getUsername() == actualUser.getUsername())
                    if  (!cohorts1.contains(cohort)) cohorts1.add(cohort);
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
//    public List<Subject> vakkenCohort(Cohort cohort){
//        List<Subject> subjects=new ArrayList<>();
//        for (String name:fullCohort(cohort).getSubjectNames()) {
//            subjects.add(subjectRepository.findBySubjectName(name));
//        }
//        return subjects;
//    }

    public List<Subject> vakkenCohort(Cohort cohort){
        List<Subject> subjects=new ArrayList<>();
        for (Subject subject:fullCohort(cohort).getSubjects()) {
            subjects.add(subject);
        }
        return subjects;
    }
    public List<String> daysOfWeek(){
        List<String> days=new ArrayList<>();
        for (Weekday day:Weekday.values()) {
            days.add(day.name().toLowerCase());
        }
        return days;
    }
    public List<String> partsOfDay(){
        List<String> parts=new ArrayList<>();
        for (PartOfDay part:PartOfDay.values()) {
            parts.add(part.name().toLowerCase());
        }
        return parts;
    }
    public Subject subjectInfo(String subjectname){
        Subject subject= new Gson().fromJson(subjectname, Subject.class);
        return subjectRepository.findBySubjectName(subject.getSubjectName());
    }
    public List<String> aantalDagen(String subjectName){
        Subject subject=subjectInfo(subjectName);
        List<String> dagen=new ArrayList<>();
        var aantal= (int) subjectRepository.findBySubjectName(subject.getSubjectName()).getHours()/URENLESBLOK;
        for (var i=0;i<aantal;i++){
            dagen.add("dag"+i+1);
        }
        return dagen;
    }
    public void vakkenPlanningCohortDefinitief(String cohortName){
        List<Subject> subjectInPlanning=vakkenCohort(cohortService.getCohort(cohortName));
        List<CourseSchedule> courses=courseSchedule(cohortRepository.findByCohortName(cohortName));
        List<Subject> subjectHours=new ArrayList<>(); // this list will cointans subjects with the sum of their planned hours
        for (CourseSchedule course:courses) {
            if (course.getStatus()==StatusCourseSchedule.DEFINITIEF)  courses.remove(course);
           // else { if (!subjectHours.contains(course.getSubject()))
//            todo deze functie afmaken
        }
    }
    public Boolean isNonTeachingDay(String date){
        //eerst van json naar een string: new Gson().fromJson(date,String.class)
        //daarna de string omzetten in localdate:LocalDate.parse(new Gson().fromJson(date,String.class))
        //daarna (dus alles achter return) zit de localdate in HolidaySchedule? zo ja=> geen les!
        System.out.printf("\n%s\n\n",date);
        var datum=new Gson().fromJson(date, HolidaySchedule.class);
        System.out.printf("\n%s\n\n",datum.toString());
        return holidays.findByLocalDate(datum.getLocalDate());
    }
}
