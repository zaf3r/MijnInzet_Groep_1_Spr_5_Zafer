package makeitwork.mijninzet.controller.CourseSchedule;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.CourseSchedule.receiveBeginClosingDate;
import makeitwork.mijninzet.service.CourseSchedule.CourseScheduleAgenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgendaController {
    @Autowired
    CourseScheduleAgenda agenda;

    @GetMapping("/test1")
    public String agendaFrontEnd(Model model){
        System.out.printf("Dit is test1/courseAgenda");
        return "CourseSchedule/courseAgenda";
    }

    @PostMapping("/agenda")
    public @ResponseBody
    String cohortsToPlan(@RequestBody receiveBeginClosingDate requestPayload) {
        var begin=requestPayload.getBegin();
        var closing=requestPayload.getClosing();
        return new Gson().toJson(agenda.Agenda(begin,closing));
    }
}
