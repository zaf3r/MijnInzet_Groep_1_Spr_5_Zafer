/*@author Peter Post, David Heeneman
 *
 *        Doel programma
 *
 *
 */
package makeitwork.mijninzet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private static final String appName = "mijnInzet";
    private static final String teamLeden = "Baseet, Bibi, David, Merel, Peter en Zafer";

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {

        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        model.addAttribute("team", teamLeden);
        return "home";
    }
    @GetMapping("/addUser")
    public String addUser(Model model){
        return "addUser";
    }

    @GetMapping("/jammerdan")
    public String jammerDan(Model model){
        return "jammerdan";
    }

    @GetMapping("/globalAvalability")
    public String addAvalability(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name);
        return "globalAvalability"; }

    @GetMapping("/incidents")
    public String addIncidents(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name); return "incidents"; }

    @GetMapping("/teacher")
    public String addPreferance(Model model) { return "voorkeur-vakken"; }

    @GetMapping("/manager")
    public String addCourse(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Guest") String name) {
        model.addAttribute("userName", name); return "addCourse"; }
}
