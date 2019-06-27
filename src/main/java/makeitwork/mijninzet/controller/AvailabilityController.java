package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Availability.GlobalAvailability.Availability;
import makeitwork.mijninzet.model.Availability.GlobalAvailability.AvailabilityForm;
import makeitwork.mijninzet.model.Availability.Weekday;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.AvailabilityRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
@RequestMapping("/availability")
public class AvailabilityController implements RetrieveUserRole{

    final Availability AVAILABILITY_LOADER = new Availability();

    @Autowired
    UserRepository userRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @GetMapping("availabilityForm")
    public String getAvailabilityForm(Principal principal, Model model) {

        User user = userRepository.findByUsername(principal.getName());
        Set<Availability> availabilitySet;

        if(availabilityRepository.findAllByUser(user).isEmpty()) {
            availabilitySet = emptyAvailabilityStarter(user);
            availabilityRepository.saveAll(availabilitySet);
        } else {
            availabilitySet = availabilityRepository.findAllByUser(user);
        }

        AvailabilityForm availabilityForm = new AvailabilityForm();

        availabilityForm.setAvailabilityMonday(AVAILABILITY_LOADER.findMonday(availabilitySet));
        availabilityForm.setAvailabilityTuesday(AVAILABILITY_LOADER.findTuesday(availabilitySet));
        availabilityForm.setAvailabilityWednesday(AVAILABILITY_LOADER.findWednesday(availabilitySet));
        availabilityForm.setAvailabilityThursday(AVAILABILITY_LOADER.findThursday(availabilitySet));
        availabilityForm.setAvailabilityFriday(AVAILABILITY_LOADER.findFriday(availabilitySet));

        model.addAttribute("availabilityForm",availabilityForm);
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);

        return "globalAvalability";
    }

    @PostMapping("postAvailabilityForm")
    public String postAvailabilityForm(@RequestParam("mondayMo") boolean mondayMo,
                                       @RequestParam("mondayAf") boolean mondayAf,
                                       @RequestParam("mondayEv") boolean mondayEv,
                                       @RequestParam("tuesdayMo") boolean tuesdayMo,
                                       @RequestParam("tuesdayAf") boolean tuesdayAf,
                                       @RequestParam("tuesdayEv") boolean tuesdayEv,
                                       @RequestParam("wednesdayMo") boolean wednesdayMo,
                                       @RequestParam("wednesdayAf") boolean wednesdayAf,
                                       @RequestParam("wednesdayEv") boolean wednesdayEv,
                                       @RequestParam("thursdayMo") boolean thursdayMo,
                                       @RequestParam("thursdayAf") boolean thursdayAf,
                                       @RequestParam("thursdayEv") boolean thursdayEv,
                                       @RequestParam("fridayMo") boolean fridayMo,
                                       @RequestParam("fridayAf") boolean fridayAf,
                                       @RequestParam("fridayEv") boolean fridayEv,
                                       Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        Set<Availability> availabilitySet = availabilityRepository.findAllByUser(user);

        setValuesAvailability(AVAILABILITY_LOADER.findMonday(availabilitySet),
                mondayMo, mondayAf, mondayEv, user);
        setValuesAvailability(AVAILABILITY_LOADER.findTuesday(availabilitySet),
                tuesdayMo, tuesdayAf, tuesdayEv, user);
        setValuesAvailability(AVAILABILITY_LOADER.findWednesday(availabilitySet),
                wednesdayMo, wednesdayAf, wednesdayEv, user);
        setValuesAvailability(AVAILABILITY_LOADER.findThursday(availabilitySet),
                thursdayMo, thursdayAf, thursdayEv, user);
        setValuesAvailability(AVAILABILITY_LOADER.findFriday(availabilitySet),
                fridayMo, fridayAf, fridayEv, user);

        availabilityRepository.saveAll(availabilitySet);
        return "redirect:/availability/availabilityForm";
    }

    /**
     * The first time a user enters the availability page, availabilities are prepared for the form.
     * @param user current session user
     * @return availabiilityForm containing all the possible options
     */
    public Set<Availability> emptyAvailabilityStarter(User user) {
        Set<Availability> availabilitySet = new LinkedHashSet<>();

        for (Weekday weekday: Weekday.values()) {
            Availability availability = new Availability();
            availability.setWeekday(weekday);
            availability.setUser(user);
            availabilitySet.add(availability);
        }
        return availabilitySet;
    }

    public void setValuesAvailability(Availability availability, boolean morning, boolean afternoon, boolean evening, User user) {
        availability.setMorning(morning);
        availability.setAfternoon(afternoon);
        availability.setEvening(evening);
        availability.setUser(user);
    }
}
