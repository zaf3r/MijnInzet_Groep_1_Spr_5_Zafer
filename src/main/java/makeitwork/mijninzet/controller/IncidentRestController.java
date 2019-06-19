package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Availability.Incident.Incident;
import makeitwork.mijninzet.model.Availability.Incident.IncidentJsonHolder;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.IncidentRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IncidentRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IncidentRepository incidentRepository;

    final Incident INCIDENT_LOADER = new Incident();

    @GetMapping("/findUser/{username}")
    public User findUserHandler(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/findincident/{year}/{week}")
    public List<Incident> findIncidentListHandler(@PathVariable("year") int year,
                                                  @PathVariable("week") int weekNumber,
                                                  Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        List<Incident> incidentList = incidentRepository.findAllByYearAndWeeknumberAndUser(year,weekNumber,user);

        if(incidentList.isEmpty()) {
            System.out.println("it's empty");
            return emptyIncidentStarter(year,weekNumber, user);
        } else {
            return incidentList;
        }
    }

    @PostMapping("/saveIncidents")
    public void saveIncidentListHandler(@RequestBody IncidentJsonHolder incidentJsonHolder,
                                        Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        List<Incident> incidentList = incidentDBLoader(incidentJsonHolder.getYear(),incidentJsonHolder.getWeek(),
                user);

        formDataLoader(incidentList, incidentJsonHolder);

        incidentRepository.saveAll(incidentList);
    }

    public List<Incident> emptyIncidentStarter(int year, int weeknumber, User user) {
        List<Incident> incidentList = new ArrayList<>();

        for (Weekday weekday : Weekday.values()) {
            Incident tempIncident = new Incident();
            tempIncident.setWeekday(weekday);
            tempIncident.setWeeknumber(weeknumber);
            tempIncident.setYear(year);
            tempIncident.setUser(user);
            incidentList.add(tempIncident);
        }
        return incidentList;
    }

    public void formDataLoader(List<Incident> incidentList, IncidentJsonHolder incidentJsonHolder) {
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.MONDAY, incidentList),
                incidentJsonHolder.isMondayMo(),incidentJsonHolder.isMondayAf(),incidentJsonHolder.isMondayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.TUESDAY, incidentList),
                incidentJsonHolder.isTuesdayMo(),incidentJsonHolder.isTuesdayAf(),incidentJsonHolder.isTuesdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.WEDNESDAY, incidentList),
                incidentJsonHolder.isWednesdayMo(),incidentJsonHolder.isWednesdayAf(),incidentJsonHolder.isWednesdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.THURSDAY, incidentList),
                incidentJsonHolder.isThursdayMo(),incidentJsonHolder.isThursdayAf(),incidentJsonHolder.isThursdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.FRIDAY, incidentList),
                incidentJsonHolder.isFridayMo(),incidentJsonHolder.isFridayAf(),incidentJsonHolder.isFridayEv());
    }

    public void setValuesIncident(Incident incident, boolean morning, boolean afternoon, boolean evening) {
        incident.setMorning(morning);
        incident.setAfternoon(afternoon);
        incident.setEvening(evening);
    }

    List<Incident> incidentDBLoader(int year, int weekNumber, User user) {
        List<Incident> incidentList = incidentRepository.findAllByYearAndWeeknumberAndUser(year,weekNumber,user);

        if(incidentList.isEmpty()){
            System.out.println("I am empty");
            incidentList = incidentStarterList(weekNumber,year, user);
        }

        return incidentList;
    }


    List<Incident> incidentStarterList(int weekNumber, int year, User user) {
        List<Incident> incidentList = new ArrayList<>();

        for(Weekday weekday : Weekday.values()) {
            Incident incident = new Incident();
            incident.setYear(year);
            incident.setWeeknumber(weekNumber);
            incident.setWeekday(weekday);
            incident.setUser(user);
            incidentList.add(incident);
        }
        return incidentList;
    }
}
