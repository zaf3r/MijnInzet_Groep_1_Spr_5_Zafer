package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Availability.GlobalAvailability.Availability;
import makeitwork.mijninzet.model.Availability.Incident.Incident;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.CourseSchedule.CourseSchedule;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.TeacherSchedule.CohortDay;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeekDTO;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Preference;
import makeitwork.mijninzet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TeacherSchedulerRestController {


    final private Role TEACHER_ROLE = new Role(1, "Docent");
    final private CohortDay COHORT_DAY_LOADER = new CohortDay();
    final private DateTimeFormatter STRING_TO_DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final private long INCREMENT_DAY_HIBERNATE_FIX = 1;

    final String MORNING = "morning";
    final String AFTERNOON = "afternoon";
    final String EVENING = "evening";
    final int MAX_PLACEMENT = 1;

    @Autowired
    CourseScheduleRepository courseScheduleRepository;

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    CohortWeekRepository cohortWeekRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    CohortDayRepository cohortDayRepository;

    //TO CLEAN
    @GetMapping("/getTeachers")
    public List<User> findTeacherListHandler() {

        Set<User> teacherSet = userRepository.findAllByRole(TEACHER_ROLE);

        for(User user : teacherSet) {
            loadAvailability(user);
        }
        List<User> userList = new ArrayList<>(teacherSet);

        return userList;
    }

    //TO CLEAN
    @GetMapping("/getPreferences")
    public List<Preference> findAllPreferencesHandler() {
        return preferenceRepository.findAll();
    }

    @GetMapping("/getCourseSchedule/{date}/{cohortName}")
        public List<CourseSchedule> findCourseScheduleHandler(@PathVariable("cohortName")String cohortName,
                                                              @PathVariable("date") String dateString) {
        Cohort cohort = cohortRepository.findByCohortName(cohortName);
        LocalDate dateCourseSchedule = parseStringToLocalDate(dateString).plusDays(INCREMENT_DAY_HIBERNATE_FIX);
        return courseScheduleRepository.findAllByCohortAndDate(cohort, dateCourseSchedule);
    }

    @GetMapping("/teacher/incident/{userName}/{dayPart}/{dateString}")
    public boolean teacherIncidentCheckHandler(@PathVariable("userName") String userName,
                                               @PathVariable("dateString") String dateString,
                                               @PathVariable("dayPart") String dayPart) {

        LocalDate dateIncident = parseStringToLocalDate(dateString).plusDays(INCREMENT_DAY_HIBERNATE_FIX);
        User user = userRepository.findByUsername(userName);
        Incident incident = incidentRepository.findIncidentByUserAndDateIncident(user,dateIncident);

        boolean noIncident;

        if(incident == null) {
            noIncident = true;
        } else {
            if(dayPart.equals(MORNING)) {
                noIncident = incident.isMorning();
            } else if (dayPart.equals(AFTERNOON)) {
                noIncident = incident.isAfternoon();
            } else {
                noIncident = incident.isEvening();
            }
        }
        return noIncident;
    }

    @GetMapping("/cohort/scheduled/{userName}/{dayPart}/{dateString}")
    public boolean teacherScheduledConstraintCheckHandler(@PathVariable("userName") String username,
                                                          @PathVariable("dayPart") String dayPart,
                                                          @PathVariable("dateString") String dateString) {


        LocalDate dateScheduled = parseStringToLocalDate(dateString).plusDays(INCREMENT_DAY_HIBERNATE_FIX);
        User teacher = userRepository.findByUsername(username);
        boolean teacherIsAlreadyScheduled;

        if(dayPart.equals(MORNING)) {
            teacherIsAlreadyScheduled =
                    (cohortDayRepository.findAllByDateAndTeacherMorning(dateScheduled, teacher).size() >= MAX_PLACEMENT);
        } else if (dayPart.equals(AFTERNOON)) {
            teacherIsAlreadyScheduled =
                    (cohortDayRepository.findAllByDateAndTeacherAfternoon(dateScheduled, teacher).size() >= MAX_PLACEMENT);
        } else {
            teacherIsAlreadyScheduled =
                    (cohortDayRepository.findAllByDateAndTeacherEvening(dateScheduled, teacher).size() >= MAX_PLACEMENT);
        }
        return teacherIsAlreadyScheduled;
    }


    @GetMapping("/cohort/weeks/{cohortName}")
    public List<Long> findCohortWeeksHandler(@PathVariable("cohortName") String cohortNaam) {
        Cohort cohort = cohortRepository.findByCohortName(cohortNaam);
        List<CohortWeek> cohortWeekList = cohortWeekRepository.findCohortWeekByCohort(cohort);
        return generateWeeklist(cohortWeekList);
    }

    public List<Long> generateWeeklist(List<CohortWeek> cohortWeekList) {
        List<Long> listOfWeekNumbers = new ArrayList<>();

        for(CohortWeek cohortWeek: cohortWeekList) {
            long weekNumber = cohortWeek.getWeekNumber();
            listOfWeekNumbers.add(weekNumber);
        }
        return listOfWeekNumbers;
    }

    @GetMapping("/cohort/days/{cohortName}/{cohortWeek}")
    public List<CohortDay> findCohortDayData(@PathVariable("cohortName") String cohortName,
                                             @PathVariable("cohortWeek") long cohortWeekNumber) {

        Cohort cohort = cohortRepository.findByCohortName(cohortName);
        CohortWeek cohortWeek = cohortWeekRepository.findCohortWeekByCohortAndWeekNumber(cohort,cohortWeekNumber);
        return cohortWeek.getCohortDayList();
    }

    @PostMapping("/saveTeacherSchedule")
    public void saveIncidentListHandler(@RequestBody CohortWeekDTO cohortWeekDTO) {
        Cohort cohort = cohortRepository.findByCohortName(cohortWeekDTO.getCohort());
        CohortWeek cohortWeek = cohortWeekRepository.findCohortWeekByCohortAndWeekNumber(cohort, cohortWeekDTO.getWeekNumber());
        cohortWeekRepository.save(cohortWeekLoader(cohortWeek, cohortWeekDTO));
    }

    public CohortWeek cohortWeekLoader(CohortWeek cohortWeek, CohortWeekDTO cohortWeekDTO) {
        cohortDayLoader(COHORT_DAY_LOADER.findDay(DayOfWeek.MONDAY, cohortWeek.getCohortDayList()),
                cohortWeekDTO.getMondayMorningTeacher(),
                cohortWeekDTO.getMondayAfternoonTeacher(),
                cohortWeekDTO.getMondayEveningTeacher());

        cohortDayLoader(COHORT_DAY_LOADER.findDay(DayOfWeek.TUESDAY, cohortWeek.getCohortDayList()),
                cohortWeekDTO.getTuesdayMorningTeacher(),
                cohortWeekDTO.getTuesdayAfternoonTeacher(),
                cohortWeekDTO.getTuesdayEveningTeacher());

        cohortDayLoader(COHORT_DAY_LOADER.findDay(DayOfWeek.WEDNESDAY, cohortWeek.getCohortDayList()),
                cohortWeekDTO.getWednesdayMorningTeacher(),
                cohortWeekDTO.getWednesdayAfternoonTeacher(),
                cohortWeekDTO.getWednesdayEveningTeacher());

        cohortDayLoader(COHORT_DAY_LOADER.findDay(DayOfWeek.THURSDAY, cohortWeek.getCohortDayList()),
                cohortWeekDTO.getThursdayMorningTeacher(),
                cohortWeekDTO.getThursdayAfternoonTeacher(),
                cohortWeekDTO.getThursdayEveningTeacher());

        cohortDayLoader(COHORT_DAY_LOADER.findDay(DayOfWeek.FRIDAY, cohortWeek.getCohortDayList()),
                cohortWeekDTO.getFridayMorningTeacher(),
                cohortWeekDTO.getFridayAfternoonTeacher(),
                cohortWeekDTO.getFridayEveningTeacher());

        return cohortWeek;
    }

    public void cohortDayLoader(CohortDay cohortDay, String teacherMorning, String teacherAfternoon, String teacherEvening) {
        cohortDay.setTeacherMorning(userRepository.findByUsername(teacherMorning));
        cohortDay.setTeacherAfternoon(userRepository.findByUsername(teacherAfternoon));
        cohortDay.setTeacherEvening(userRepository.findByUsername(teacherEvening));
    }

    public void loadAvailability(User user) {
        List<Availability> availabilityList = availabilityRepository.findAllByUser(user);
        user.setAvailabilityList(availabilityList);
    }

    public LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, STRING_TO_DATE_FORMATER);
    }
}
