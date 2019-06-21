
package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController implements RetrieveUserRole{

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {
        String appName = "mijnInzet";
        String teamLeden = "Baseet, Bibi, David, Merel, Peter en Zafer";
        User user = userRepository.findByUsername(principal.getName());
        Role roleCurrentUser = retrieveRole(userRepository, principal);

        model.addAttribute("user",user);
        model.addAttribute("roleCurrentUser", roleCurrentUser);
        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        model.addAttribute("team", teamLeden);
        return "home";
    }

//    @GetMapping("/addUser")
//    public String addUser(Model model){
//        return "addUser";
//    }


    @GetMapping("/globalAvalability")
    public String addAvalability(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name);
        return "globalAvalability"; }

    @GetMapping("/incident")
    public String addIncidents(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name);
        return "incidents-form"; }

    @GetMapping("/teacher")
    public String addPreferance(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name);
        return "voorkeur-vakken"; }

    @GetMapping("/manager")
    public String addSubject(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name);
        return "courseManagement"; }
}
