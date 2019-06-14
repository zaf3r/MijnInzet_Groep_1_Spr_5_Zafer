package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Incident.Incident;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.IncidentRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        List<Incident> incidentList = incidentRepository.findAllByYearAndWeeknumberAAndUser(year,weekNumber,user);

        if(incidentList.isEmpty()) {
            return incidentList;
        } else {
            return null;
        }
    }

    @PostMapping("saveIncidents/{incidentList}")
    public void saveIncidentListHandler(@PathVariable("incidentList") List<Incident> incidentList,
                                        Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        for (Incident incident:incidentList) {
            incident.setUser(user);
        }

        System.out.println("This endpoint doesn't save anything for now");
    }

}
