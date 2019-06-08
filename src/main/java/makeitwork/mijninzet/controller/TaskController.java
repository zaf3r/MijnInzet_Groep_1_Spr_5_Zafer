package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
        (value ="/teacher")
public class TaskController extends AbstractController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    VacatureService vacatureService;

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("allTasks", tasks2React(user));
        return "taskOverview";
    }

    @GetMapping("/showTask/{task}")  //th:action
    public String TaskDetailHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Model model) {
        taak = opening(taakId);
        model.addAttribute("taak", taak);
        return "showTask"; //html
    }

    @GetMapping("/taskSave/{taskId}") //mapping bij voor de view
    public String ApplicationHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Principal principal) {
        taak = opening(taakId);
        vacatureService.addTask(taak, usersRepository.findByUsername(principal.getName()));
        return "redirect:/taskOverview";
    }

    @GetMapping("/myTasks")
    public String MyTaskHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("myTasks", myTaskList(user));
        return "myTasks";
    }

    @PostMapping("/taskDelete/{taskId}")
    public String DeleteTaskHandler(@ModelAttribute("myTask") Task myTask, @RequestParam("taskId") String taakId, Principal principal){
        myTask = opening(taakId);
        System.out.println(myTask);
        vacatureService.removeTask(myTask, usersRepository.findByUsername(principal.getName()));
        return "redirect:/myTasks";
    }

    //haalt alles uit database
    private List<Task> allTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        sortTasks(tasks);
        return tasks;
    }

    private Task opening(String taskId) {
        return this.taskRepository.findDocumentById(taskId);
    }

    private List<Task> sortTasks(List<Task> tasks) {
        Collections.sort(tasks, (a, b) -> {
            return a.getTitel().compareTo(b.getTitel());
        });
        return tasks;
    }

    //lijst met vacatures van Docent (zit ook al in Service)
//    private Set<Application> findApplicationByUsername(User user){
//        Set<Application> applications = sollicitatieRepository.findAllByUser(user);
//        return applications;
//    }

    //lijst met taken waar de docent nog op kan reageren
    private List<Task> tasks2React(User user) {
        List<Task> tasks = allTasks();
        System.out.println("All: " + tasks);
        List<Task> myTasks = vacatureService.getAllTasks(user);
        System.out.println("My tasks: " + myTasks);
        List<Task> possibleTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (!isContained(t, myTasks)) {
                possibleTasks.add(t);
            }
        }
        System.out.println(possibleTasks);
        return possibleTasks;
    }

    public List<Task> myTaskList(User user) {
        List<Task> tasks = allTasks();
        List<Task> myTasks = vacatureService.getAllTasks(user);
        List<Task> possibleTasks = new ArrayList<>();
        for (Task t : myTasks) {
            if (!doesContaine(t, tasks)) {
                possibleTasks.add(t);
            }
        }
        return possibleTasks;
    }

    public boolean isContained(Task t, List<Task> listTask) {
        for (Task t2 : listTask) {
            if (t.getId().equals(t2.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean doesContaine(Task t, List<Task> listTask) {
        for (Task t2 : listTask) {
            if (t.getId() == (t2.getId())) {
                return true;
            }
        }
        return false;
    }
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
//        this.taskRepository.deleteById(id)
// }
//        tasks.forEach((i)->myTasks.remove(i));
////         tasks.removeAll(myTasks); // levert tasks op waaruit alle eerder gereageerde taken zijn verwijderd
//        List<Task> diff = tasks.stream().filter(e -> !myTasks.contains(e)).collect(Collectors.toList());

