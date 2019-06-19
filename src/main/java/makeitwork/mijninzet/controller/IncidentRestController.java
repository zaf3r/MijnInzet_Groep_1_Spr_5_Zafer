package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Availability.Incident.IncidentJsonHolder;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.Availability.Incident.Incident;
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
            return emptyIncidentStarter(year,weekNumber);
        } else {
            return incidentList;
        }
    }

    @PostMapping("saveIncidents/{incidents}")
    public void saveIncidentListHandler(@PathVariable("incidents")IncidentJsonHolder incidentJsonHolder,
                                        Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        System.out.println(incidentJsonHolder);

        System.out.println("This endpoint doesn't save anything for now");
    }

    public List<Incident> emptyIncidentStarter(int year, int weeknumber) {
        List<Incident> incidentList = new ArrayList<>();

        for (Weekday weekday : Weekday.values()) {
            Incident tempIncident = new Incident();
            tempIncident.setWeekday(weekday);
            tempIncident.setWeeknumber(weeknumber);
            tempIncident.setYear(year);
            incidentList.add(tempIncident);
        }
        return incidentList;
    }
}
