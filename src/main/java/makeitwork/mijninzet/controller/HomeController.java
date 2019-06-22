
package makeitwork.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {
        String appName = "mijnInzet";
        String teamLeden = "Baseet, Bibi, David, Merel, Peter en Zafer";

        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        model.addAttribute("team", teamLeden);
        return "home";
    }

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
