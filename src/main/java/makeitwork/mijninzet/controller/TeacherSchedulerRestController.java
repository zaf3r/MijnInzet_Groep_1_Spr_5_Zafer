package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.TeacherSchedule.CohortDay;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.CohortWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherSchedulerRestController {

    @Autowired
    CohortWeekRepository cohortWeekRepository;

    @Autowired
    CohortRepository cohortRepository;

    @GetMapping("/cohort/weeks/{cohortName}")
    public List<Integer> findCohortWeeksHandler(@PathVariable("cohortName") String cohortNaam) {
        Cohort cohort = cohortRepository.findByCohortName(cohortNaam);
        List<CohortWeek> cohortWeekList = cohortWeekRepository.findCohortWeekByCohort(cohort);
        return generateWeeklist(cohortWeekList);
    }

    public List<Integer> generateWeeklist(List<CohortWeek> cohortWeekList) {
        List<Integer> listOfWeekNumbers = new ArrayList<>();

        for(CohortWeek cohortWeek: cohortWeekList) {
            int weekNumber = cohortWeek.getWeekNumber();
            listOfWeekNumbers.add(weekNumber);
        }
        return listOfWeekNumbers;
    }

    @GetMapping("/cohort/days/{cohortName}/{cohortWeek}")
    public List<CohortDay> findCohortDayData(@PathVariable("cohortName") String cohortName,
                                             @PathVariable("cohortWeek") int cohortWeekNumber) {

        Cohort cohort = cohortRepository.findByCohortName(cohortName);
        CohortWeek cohortWeek = cohortWeekRepository.findCohortWeekByCohortAndWeekNumber(cohort,cohortWeekNumber);
        return cohortWeek.getCohortDayList();
    }

//    @PostMapping("/saveSubjectSchule")
//    public void saveIncidentListHandler(/*@RequestBody DTO TO DO*/) {
//
//        formDataLoader(incidentList, incidentJsonHolder);
//
//        incidentRepository.saveAll(incidentList);
//    }
}
