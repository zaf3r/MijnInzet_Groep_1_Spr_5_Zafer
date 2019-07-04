package makeitwork.mijninzet.controller.Incident;

import makeitwork.mijninzet.model.Availability.Incident.Incident;
import makeitwork.mijninzet.model.Availability.Incident.IncidentDTO;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.IncidentRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IncidentRestController {

    //Ordinal values of Calender Api Days
    final int MONDAY = 2;
    final int TUESDAY = 3;
    final int WEDNESDAY = 4;
    final int THURSDAY = 5;
    final int FRIDAY = 6;

    final long DATE_CORRECTION_HIBERNATE = 1;


    Calendar calendar = Calendar.getInstance();
    final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

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
            return emptyIncidentStarter(year,weekNumber, user);
        } else {
            return incidentList;
        }
    }

    @PostMapping("/saveIncidents")
    public void saveIncidentListHandler(@RequestBody IncidentDTO incidentDTO,
                                        Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        List<Incident> incidentList = incidentDBLoader(incidentDTO.getYear(), incidentDTO.getWeek(),
                user);

        formDataLoader(incidentList, incidentDTO);

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
            tempIncident.setMorning(true);
            tempIncident.setAfternoon(true);
            tempIncident.setEvening(true);
            incidentList.add(tempIncident);
        }
        return incidentList;
    }

    public void formDataLoader(List<Incident> incidentList, IncidentDTO incidentDTO) {
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.MONDAY, incidentList),
                incidentDTO.isMondayMo(), incidentDTO.isMondayAf(), incidentDTO.isMondayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.TUESDAY, incidentList),
                incidentDTO.isTuesdayMo(), incidentDTO.isTuesdayAf(), incidentDTO.isTuesdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.WEDNESDAY, incidentList),
                incidentDTO.isWednesdayMo(), incidentDTO.isWednesdayAf(), incidentDTO.isWednesdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.THURSDAY, incidentList),
                incidentDTO.isThursdayMo(), incidentDTO.isThursdayAf(), incidentDTO.isThursdayEv());
        setValuesIncident(INCIDENT_LOADER.findDay(Weekday.FRIDAY, incidentList),
                incidentDTO.isFridayMo(), incidentDTO.isFridayAf(), incidentDTO.isFridayEv());
    }

    public void setValuesIncident(Incident incident, boolean morning, boolean afternoon, boolean evening) {
        incident.setMorning(morning);
        incident.setAfternoon(afternoon);
        incident.setEvening(evening);
    }

    List<Incident> incidentDBLoader(int year, int weekNumber, User user) {
        List<Incident> incidentList = incidentRepository.findAllByYearAndWeeknumberAndUser(year,weekNumber,user);

        if(incidentList.isEmpty()){
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

            addDateToIncident(incident);

            incidentList.add(incident);
        }
        return incidentList;
    }

    void addDateToIncident(Incident incident) {
        calendar.set(Calendar.YEAR, incident.getYear());
        calendar.set(Calendar.WEEK_OF_YEAR, incident.getWeeknumber());
        calendar.set(Calendar.DAY_OF_WEEK, retrieveOrdinalValueOfDay(incident.getWeekday()));
        LocalDate dateIncident = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        incident.setDateIncident(dateIncident.plusDays(DATE_CORRECTION_HIBERNATE));
    }

    public int retrieveOrdinalValueOfDay(Weekday weekday) {
        if(weekday.equals(Weekday.MONDAY)){
            return MONDAY;
        } else if(weekday.equals(Weekday.TUESDAY)) {
            return TUESDAY;
        } else if(weekday.equals(Weekday.WEDNESDAY)) {
            return WEDNESDAY;
        } else if(weekday.equals(Weekday.THURSDAY)) {
            return THURSDAY;
        } else {
            return FRIDAY;
        }
    }
}
