
package makeitwork.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @GetMapping ("/")
    public String index() {
    return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
//
//    @GetMapping("/home")
//    public String home(Model model,
//                       @RequestParam(value = "name", required = false,
//                               defaultValue = "Guest") String name) {
//        String appName = "mijnInzet";
//        String teamLeden = "Baseet, Bibi, David, Merel, Peter en Zafer";
//
//        model.addAttribute("name", name);
//        model.addAttribute("title", appName);
//        model.addAttribute("team", teamLeden);
//        return "/home";
//
//    }
//    @GetMapping("/addUser")
//    public String addUser(Model model){
//        return "addUser";
//    }

    @GetMapping("/globalAvalability")
    public String addAvalability(Model model) { return "globalAvalability"; }

    @GetMapping("/teacher")
    public String addPreferance(Model model) { return "voorkeur-vakken"; }
}
