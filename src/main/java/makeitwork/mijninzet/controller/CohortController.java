package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.TeacherSchedule.CohortDay;
import makeitwork.mijninzet.model.TeacherSchedule.CohortWeek;
import makeitwork.mijninzet.model.User;

import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class CohortController implements RetrieveUserRole {

    final private int DEFAULT_FIRST_WEEK = 1;
    final private long WEEK_INCREMENTATION = 1;
    final private String USERNAME_DEFAULT_USER = "Geen docent";
    final private long INCREMENT_DAY_HIBERNATE_FIX = 1;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private CohortRepository coRepo;
    @Autowired
    private CohortService cohortService;
    @Transient
    private final String COORDINATOR = "Manager";

    private User actualUser;

    @GetMapping("/cohort")
    public String CohortHandler(Model model, Principal principal){
        Cohort cohort = new Cohort();
        model.addAttribute("attr1", cohort);
        model.addAttribute("coordinators", getCoordinators());
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);
        return "cohort";
    }

    @GetMapping("/cohortSubject")
    public String AddSubjectHandler(Model model, Principal principal){
        actualUser = userRepository.findByUsername(principal.getName());
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);
        return "cohortSubject";
    }

    public List<User> getCoordinators(){
        List<User> allCoordinators = cohortService.userList(COORDINATOR);
        return allCoordinators;
    }

    @PostMapping("/saveCohort")
    public String saveCohort(@ModelAttribute("saveCohort")Cohort cohort, @RequestParam("coordinator")User co, Model model) {
        String cohortName = cohort.getCohortName();

        if (coRepo.findByCohortName(cohortName)!= null){
            System.out.println(coRepo.findByCohortName(cohortName));
        } else {
            cohort.setUser(co);
            cohort.setStartDate(cohort.getStartDate().plusDays(INCREMENT_DAY_HIBERNATE_FIX));
            cohort.setEndDate(cohort.getEndDate().plusDays(INCREMENT_DAY_HIBERNATE_FIX));
            generateWeeksAndDays(cohort);
        }
        return "redirect:/manager/cohort";
    }

    public void generateWeeksAndDays(Cohort cohort) {
        List<CohortWeek> cohortWeekList = new ArrayList<>();
        int weekNumber = DEFAULT_FIRST_WEEK;
        LocalDate startDate = cohort.getStartDate();

        while(startDate.isBefore(cohort.getEndDate()) || startDate.isEqual(cohort.getEndDate())) {
            CohortWeek cohortWeek = new CohortWeek();
            cohortWeek.setCohort(cohort);
            cohortWeek.setWeekNumber(weekNumber);
            cohortWeek.setCohortDayList(generateCohortDays(startDate, cohortWeek));
            cohortWeekList.add(cohortWeek);

            startDate = startDate.plusWeeks(WEEK_INCREMENTATION);
            weekNumber++;
        }
        cohort.setCohortWeekList(cohortWeekList);
        coRepo.save(cohort);
        //TO DO: MAKE IT CLEAN - EVERYTHING A SINGLE PURPOSE
    }

    public User retrieveDefaultTeacher() {
        return userRepository.findByUsername(USERNAME_DEFAULT_USER);
    }

    public List<CohortDay> generateCohortDays(LocalDate date, CohortWeek cohortWeek) {
        List<CohortDay> cohortDayList = new ArrayList<>();

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if(dayOfWeek.equals(dayOfWeek.SATURDAY) | dayOfWeek.equals(dayOfWeek.SUNDAY)) {
                // Do nothing if days equal saturday or sunday
            } else {
                CohortDay cohortDay = new CohortDay();
                cohortDay.setDate((date.with(dayOfWeek).plusDays(INCREMENT_DAY_HIBERNATE_FIX)));
                cohortDay.setTeacherMorning(retrieveDefaultTeacher());
                cohortDay.setTeacherAfternoon(retrieveDefaultTeacher());
                cohortDay.setTeacherEvening(retrieveDefaultTeacher());
                cohortDay.setCohortWeek(cohortWeek);
                cohortDay.setDayOfWeek(dayOfWeek);
                cohortDayList.add(cohortDay);
            }
        }
        return cohortDayList;

    }
}
