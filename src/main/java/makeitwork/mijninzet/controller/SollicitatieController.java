package makeitwork.mijninzet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//        Role role = retrieveRole(userRepository,principal);
//        model.addAttribute("roleUser", role);
        return "sollicitaties";
    }

    @PostMapping("/saveSollicitaties")
    public String saveApplicatonHandler (Principal principal, Model model,
                                         @RequestParam("taskId") int taakId, @RequestParam("docent")User user,
                                         @ModelAttribute Task resultaat) {
        resultaat = getTask(taakId);
        System.out.println("taak is : " + resultaat);
        System.out.println("docent is : " + user);
        vacatureService.saveApprovedTasks(user, resultaat);
        Role role = retrieveRole(userRepository,principal);
        model.addAttribute("roleUser", role);
        return "redirect:/manager/sollicitaties";
    }

    @GetMapping("/taak")
    public String addnewTaskHandler (Model model) {
        Task task = new Task();
        model.addAttribute("createTask", task);
        return "newtask";
    }

    @PostMapping("/createNewTask")
    public @ResponseBody String saveTask (@RequestBody String payload){
        System.out.println(payload);
        Task task = deSerializeTemp(payload);
        vacatureService.saveTasks(task);
        return "redirect: /manager/taak";
    }

    public Task deSerializeTemp(String requestPayload) {
        Task task = new Gson().fromJson(requestPayload, Task.class);
        return task;
    }


//    @PostMapping("/createNewTask")
//    public String saveNewTaskHandler(String titel, String description, int uren, String locatie,
//                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDate sluitdatum,
//                                     String startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDate einddatum, String longD){
//        Task task = new Task (titel, description, uren, locatie, sluitdatum, startDate, einddatum, longD);
//        taskRepository.save(task);
//        return "redirect:/manager/taak";
//    }

//TODO bij geen sollicitaties: bericht momenteel nietmand gesolliciteerd

    public Task getTask(int id){
        return taskRepository.findTaskById(id);
    }

    public User getUser (int id){
        return vacatureRepository.findUserById(id);
    }

}
