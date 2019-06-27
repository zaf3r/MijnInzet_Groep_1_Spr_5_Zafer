package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.TeacherSchedule.CohortDay;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeekDTO;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CohortWeekRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherSchedulerRestController {

    final private CohortDay COHORT_DAY_LOADER = new CohortDay();

    @Autowired
    CohortWeekRepository cohortWeekRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Autowired
    UserRepository userRepository;

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

    public CohortDay cohortDayLoader(CohortDay cohortDay, String teacherMorning, String teacherAfternoon, String teacherEvening) {
        cohortDay.setTeacherMorning(userRepository.findByUsername(teacherMorning));
        cohortDay.setTeacherAfternoon(userRepository.findByUsername(teacherAfternoon));
        cohortDay.setTeacherEvening(userRepository.findByUsername(teacherEvening));
        return cohortDay;
    }
}
