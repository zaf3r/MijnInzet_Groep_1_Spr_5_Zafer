package makeitwork.mijninzet.controller;



import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.Teacher;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UsersRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import makeitwork.mijninzet.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
        (value ="/teacher")
public class TaskController extends AbstractController{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    VacatureService vacatureService;

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model, Principal principal){
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("allTasks", tasks2React(allTasks(), user));
        return "taskOverview";
    }

    @GetMapping("/showTask/{task}")  //th:action
    public String TaskDetailHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Model model) {
        taak = opening(taakId);
        model.addAttribute("taak",taak);
        return "showTask"; //html
    }

    @GetMapping("/taskSave/{taskId}") //mapping bij voor de view
    public String ApplicationHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Principal principal){
        Teacher teacher = new Teacher();
        teacher.setId(usersRepository.findByUsername(principal.getName()).getId());
        taak = opening(taakId);
        vacatureService.addTask(taak, teacher);
        return "taskOverview";
    }
    //haalt alles uit database
    private List<Task> allTasks(){
        List<Task> tasks = this.taskRepository.findAll();
        sortTasks(tasks);
        return tasks;
    }

    private Task opening(String taskId){
        return this.taskRepository.findDocumentById(taskId);
    }

    private List<Task> sortTasks(List<Task> tasks){
        Collections.sort(tasks,(a,b) -> {return a.getTitel().compareTo(b.getTitel());});
        return tasks;
    }

    //lijst met vacatures van Docent (zit ook al in Service)
//    private Set<Application> findApplicationByUsername(User user){
//        Set<Application> applications = sollicitatieRepository.findAllByUser(user);
//        return applications;
//    }

    //lijst met taken waar de docent nog op kan reageren
    private List<Task> tasks2React(List<Task> tasks, User user){
        // taken waar <user> eerder op reageerde uit database halen
//        User user = usersRepository.findByUsername(principal.getName());
        Teacher teacher = new Teacher();
        teacher.setId(user.getId());

        Set<Task> listTasks = new HashSet<>(tasks);
        Set<Task> reacted = vacatureService.getAllTasks(teacher);
        listTasks.removeAll(reacted); // levert tasks op waaruit alle eerder gereageerde taken zijn verwijderd
        return tasks;
    }

//    private String currentUserName(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            return authentication.getName();
//        } else return "";
//    }
////
//    @PutMapping
//    public void insert(@RequestBody Task task){
//        this.taskRepository.insert(task);
//    }
//
//    @PostMapping
//    public void update(@RequestBody Task task){
//        this.taskRepository.save(task);
//    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable ("id") String id){
//        this.taskRepository.deleteById(id);
//
//    }

}
