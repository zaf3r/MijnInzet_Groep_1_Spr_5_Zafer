package makeitwork.mijninzet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import makeitwork.mijninzet.controller.RetrieveUserRole;
import makeitwork.mijninzet.model.*;
import makeitwork.mijninzet.model.Availability.PartOfDay;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



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
//    static final String STATUS_PLANNING_COHORT=StatusCourseSchedule.DEFINITIEF.ordinal();

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

    private List<CourseSchedule> scheduledCourses(){
        //list of all courses
        return courseScheduleRepository.findAll();
    }
    private List<Cohort> cohorts(List<CourseSchedule> schedules) {
        //a list of courses is converted into a list of unique cohorts
        List<Cohort> cohorts=new ArrayList<>();
        List<CourseSchedule> schedules1 = new ArrayList<>();
        for (CourseSchedule course : schedules) {
            for (Cohort cohort:cohorts) {
                if (course.getCohort().getCohortId()==cohort.getCohortId()) break;
            }
            cohorts.add(course.getCohort());
            }
        return cohorts;
    }
    private List<CourseSchedule> finalPlannedCourses(){
        //all courses with status=DEFINITIEF (planning is casted in concrete)
        List<CourseSchedule> courses=new ArrayList<>();
        List<CourseSchedule> allCourses =courseScheduleRepository.findAll();
        //todo de definitiefe cohorts verwijderen
        return courses;
    }
    public List<Cohort> plannedCohorts(){
        return cohorts(finalPlannedCourses());
    }
    public List<Cohort> cohortsToPlan(Principal principal){
        //a list of cohorts in need for planning from the actual user of the system
        User user=userRepository.findByUsername(principal.getName());
        List<Cohort> cohorts=cohortRepository.findByUser((user));
        System.out.printf("\n\nhoeveel in cohorts %d\n\n",cohorts.size());
//        this code prevents a stackoverflow due to bad design in the class CohortWeek
//        the list(Cohortweek> is emptied and thereby an indefinite loop via Gson/Jackson
//        prevented.
        for (Cohort cohort: cohorts) {
            List<CohortWeek> cohortWeek = new ArrayList<>();
            cohort.setCohortWeekList(cohortWeek);
        }
        List<Cohort> cohorts1=new ArrayList<>();
        if(!plannedCohorts().isEmpty()) cohorts.removeAll(plannedCohorts());
//                System.out.printf("\n\ndit is cohorts1: %s\n\n",cohorts1);
//                System.out.printf("\n\ndit is cohort: %s\n\n",cohort);
//
        listCohortEmpty(cohorts);
        return cohorts;
    }
    private List<Cohort> listCohortEmpty(List<Cohort> cohorts){
        //a list with one cohort, if the original list is empty
        //the cohort in the list contains a meaningfull message for the user
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

    public Cohort cohort(String cohortName){
        return cohortRepository.findByCohortName(cohortName);
    }
    public Subject subject(String subjectName){
        return subjectRepository.findBySubjectName(subjectName);
    }
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
    public Boolean isNonTeachingDay(LocalDate date){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd\"");
//        LocalDate day = LocalDate.parse(date, formatter);
        System.out.printf("\n\n datum om mee te zoeken%\n\n",date);
        return holidays.findByLocalDate(date);
    }
    public CourseSchedule storeCourse(receiveCourse course){
        CourseSchedule schedule=new CourseSchedule();
        schedule.setCohort(cohortRepository.findByCohortName(course.getCohortName()));
        schedule.setSubject(subjectRepository.findBySubjectName(course.getSubjectName()));
        schedule.setDate(course.getDate());
        switch (course.getPartOfDay()){
            case "OCHTEND": schedule.setPartOfDay(PartOfDay.OCHTEND); break;
            case "MIDDAG": schedule.setPartOfDay(PartOfDay.MIDDAG); break;
            case "AVOND": schedule.setPartOfDay(PartOfDay.AVOND); break;
            default: break;
        }
        if (course.getStatus()==null) schedule.setStatus(StatusCourseSchedule.INPLANNING);
        else {schedule.setStatus(StatusCourseSchedule.DEFINITIEF);}
        return courseScheduleRepository.saveAndFlush(schedule);
    }
}
