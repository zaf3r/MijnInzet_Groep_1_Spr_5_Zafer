package makeitwork.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserFrontend {
    @GetMapping("/userForm")
    public String newUserForm (Model model){
        return "crudUser";
    }
}
