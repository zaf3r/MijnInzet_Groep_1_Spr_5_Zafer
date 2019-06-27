package makeitwork.mijninzet.controller;

import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.SollicitatieDTO;
import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class SollicitatieController {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private VacatureRepository vacatureRepository;

    @Autowired
    VacatureService vacatureService;

    private User selectedUser;


    @GetMapping("/sollicitaties")
    public String SollicitatieHandler (Model model) {
        List<Task> tasks = vacatureService.allApplications();
        model.addAttribute("tasks", tasks);
        Task.TaskStatus[] enums = Task.TaskStatus.values();
        model.addAttribute ( "statussen", enums);
        return "sollicitaties";
    }

    @PostMapping("/saveSollicitaties")
    public String saveApplicatonHandler (@RequestParam("taskId") int taakId,@RequestParam("userId") int userId, @ModelAttribute Task resultaat, User docent ) {
        resultaat = getTask(taakId);
        System.out.println("taak is : " + resultaat);
        docent = getUser(userId);
        System.out.println("docent is : " + docent);
        vacatureService.saveApprovedTasks(docent, resultaat);
//        model.addAttribute("naam", resultaat.getTitel());
//        model.addAttribute("status", resultaat.getTaskStatus());
//        model.addAttribute("docent", docent.getUsername());
        return "redirect: /saveSollicitaties";
    }

    public Task getTask(int id){
        return taskRepository.findTaskById(id);
    }

    public User getUser (int id){
        return vacatureRepository.findUserById(id);
    }
}
