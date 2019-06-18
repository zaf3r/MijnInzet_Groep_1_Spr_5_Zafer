package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Calendar;

@Controller
@RequestMapping("availability")
public class IncidentController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("incidentsForm")
    public String getIncidentsFormHandler(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Calendar calendar = Calendar.getInstance();
        


        return "incidents-form";
    }


}
