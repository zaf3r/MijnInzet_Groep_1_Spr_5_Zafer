package makeitwork.mijninzet.controller.CourseSchedule;

import com.google.gson.Gson;
import makeitwork.mijninzet.model.CourseSchedule.receiveBeginClosingDate;
import makeitwork.mijninzet.service.CourseSchedule.CourseAgendaService;
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
    CourseAgendaService agenda;

    @GetMapping("/test1")
    public String agendaFrontEnd(Model model) {
        return "CourseSchedule/courseAgenda";
    }

    @PostMapping("/agenda")
    public @ResponseBody
    String cohortsToPlan(@RequestBody receiveBeginClosingDate requestPayload) {
        var begin = requestPayload.getBegin();
        var closing = requestPayload.getClosing();
        var cohortName = requestPayload.getCohortName();
        return new Gson().toJson(agenda.Agenda(begin, closing, cohortName));
    }

    @PostMapping("/cohort")
    public @ResponseBody
    String workshopForCohort(@RequestBody receiveBeginClosingDate requestPayload) {
        var begin = requestPayload.getBegin();
        var closing = requestPayload.getClosing();
        var cohortName = (String) requestPayload.getCohortName();
        return new Gson().toJson(agenda.cohort(cohortName));
    }
}
