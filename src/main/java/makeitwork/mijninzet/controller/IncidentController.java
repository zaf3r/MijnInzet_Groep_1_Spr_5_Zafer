package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Availability.Incident.Incident;
import makeitwork.mijninzet.model.Availability.Incident.IncidentForm;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.IncidentRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("availability")
public class IncidentController {

    Incident INCIDENT_LOADER = new Incident();

    @Autowired
    UserRepository userRepository;

    @Autowired
    IncidentRepository incidentRepository;

    @GetMapping("incidentsForm")
    public String getIncidentsFormHandler(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Incident> incidentList;
                Calendar calendar = Calendar.getInstance();
        int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        if(incidentRepository.findAllByYearAndWeeknumberAndUser(year,weekNumber,user).isEmpty()){
            incidentList = incidentStarterList(weekNumber,year);
        } else {
            incidentList = incidentRepository.findAllByYearAndWeeknumberAndUser(year,weekNumber,user);
        }

        IncidentForm incidentForm = loadFormData(incidentList);

        model.addAttribute("incidentForm",incidentForm);

        return "incidents-form";
    }

    public List<Incident> incidentStarterList(int weekNumber, int year) {
        List<Incident> incidentList = new ArrayList<>();

        for(Weekday weekday : Weekday.values()) {
            Incident incident = new Incident();
            incident.setYear(year);
            incident.setWeeknumber(weekNumber);
            incident.setWeekday(weekday);
            incidentList.add(incident);
        }
        return incidentList;
    }

    public IncidentForm loadFormData(List<Incident> incidentList) {
        IncidentForm incidentForm = new IncidentForm();
        incidentForm.setIncidentMonday(INCIDENT_LOADER.findDay(Weekday.MONDAY,incidentList));
        incidentForm.setIncidentTuesday(INCIDENT_LOADER.findDay(Weekday.TUESDAY,incidentList));
        incidentForm.setIncidentWednesday(INCIDENT_LOADER.findDay(Weekday.WEDNESDAY,incidentList));
        incidentForm.setIncidentThursday(INCIDENT_LOADER.findDay(Weekday.THURSDAY,incidentList));
        incidentForm.setIncidentFriday(INCIDENT_LOADER.findDay(Weekday.FRIDAY,incidentList));

        return incidentForm;
    }


}