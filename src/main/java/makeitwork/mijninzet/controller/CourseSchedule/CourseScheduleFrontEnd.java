package makeitwork.mijninzet.controller.CourseSchedule;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseScheduleFrontEnd {
    @GetMapping("/courseSchedule")
    public String scheduleFrontEnd(Model model) {
        return "newSchedule";
    }

    @GetMapping("/holidays")
    public String holidaysFrontEnd(Model model) {
        return "holidays";
    }


    @GetMapping("/test")
    public String testFrontEnd(Model model) {
        return "test";
    }


    @GetMapping("/test1")
    public String test1FrontEnd(Model model){
        return "test1";
    }

}
