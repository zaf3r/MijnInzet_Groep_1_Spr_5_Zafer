package makeitwork.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseScheduleFrontEnd {
    @GetMapping("/courseSchedule")
    public String scheduleFrontEnd(Model model){
        return "courseSchedule";
    }
}
