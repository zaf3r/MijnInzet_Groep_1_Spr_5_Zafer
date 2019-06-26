package makeitwork.mijninzet.controller;

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
    public String SollicitatieHandler (Model model, Task task) {
        List<Task> tasks = vacatureService.allApplications(task);
        model.addAttribute("tasks", tasks);
        Task.TaskStatus[] enums = Task.TaskStatus.values();
        model.addAttribute ( "statussen", enums);
        return "sollicitaties";
    }

    @PostMapping("/saveSollicitaties")

    public @ResponseBody String ApproveHandler (@RequestBody String requestPayload) {

//        vacatureService.saveApprovedTasks(teacher, task);
        return "/home";
    }








}
