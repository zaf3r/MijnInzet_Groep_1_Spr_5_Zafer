package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class TaskFrontend implements RetrieveUserRole {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/newTaskStart")
    public String newUserForm(Model model, Principal principal) {

        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);

        return "newtask";
    }
}


