package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class SollicitatieController implements  RetrieveUserRole {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private VacatureRepository vacatureRepository;

    @Autowired
    VacatureService vacatureService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/sollicitaties")
    public String SollicitatieHandler (Model model, Principal principal) {
        List<Task> tasks = vacatureService.allApplications();
        model.addAttribute("tasks", tasks);
        Task.TaskStatus[] enums = Task.TaskStatus.values();
        model.addAttribute ( "statussen", enums);
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);
        return "sollicitaties";
    }

    @PostMapping("/saveSollicitaties")
    public String saveApplicatonHandler (@RequestParam("taskId") int taakId,@RequestParam("userId") int userId, @ModelAttribute Task resultaat, User docent ) {
        resultaat = getTask(taakId);
        System.out.println("taak is : " + resultaat);
        docent = getUser(userId);
        System.out.println("docent is : " + docent);
        vacatureService.saveApprovedTasks(docent, resultaat);
        return "redirect:/manager/saveSollicitaties";
    }

    public Task getTask(int id){
        return taskRepository.findTaskById(id);
    }

    public User getUser (int id){
        return vacatureRepository.findUserById(id);
    }
}
