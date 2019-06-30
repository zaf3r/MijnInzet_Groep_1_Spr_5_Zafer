package makeitwork.mijninzet.controller.CourseSchedule;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.CourseSchedule.receiveBeginClosingDate;
import makeitwork.mijninzet.service.CourseSchedule.CourseScheduleAgenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgendaController {
    @Autowired
    CourseScheduleAgenda agenda;
    @Autowired



    @PostMapping("/agenda")
    public @ResponseBody
    String cohortsToPlan(@RequestBody receiveBeginClosingDate requestPayload) {
        var begin=requestPayload.getBegin();
        var closing=requestPayload.getClosing();
        return new Gson().toJson(agenda.Agenda(begin,closing));
    }
}
