package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class VacatureController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    VacatureService vacatureService;
    @Autowired
    UserRepository usersRepository;

    @GetMapping("/taskOverview")
    public String MenuHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("allTasks", tasks2React(user));
        return "taskOverview";
    }

    @GetMapping("/showTask/{task}")
    public String TaskDetailHandler(@RequestParam int taskId, Model model) {
        Task taak = getTask(taskId);
        model.addAttribute("taak", taak);
        model.addAttribute("deadline", returnDays(taak));
        return "showTask"; //html
    }

    @GetMapping("/taskSave/{taskId}")
    public String ApplicationHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") int taakId, Principal principal) {
        taak = getTask(taakId);
        vacatureService.addTask(taak, usersRepository.findByUsername(principal.getName()));
        return "redirect:/teacher/taskOverview";
    }

//TODO checken versie Baseet
    @GetMapping("/myTasks")
    public String MyTaskHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("myTasks", myTaskList(user));
        return "myTasks";
    }

    @GetMapping ("/myApplications")
    public String MyApplicationsHandler(Model model, Principal principal){
        User user = usersRepository.findByUsername(principal.getName());
        List<Task> myApplications = user.getSollicitaties();
        model.addAttribute("takenToDo", myApplications);
        return "myApplications";
    }

    public Task getTask(int id){
       return taskRepository.findTaskById(id);
    }

    public List<Task> allTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

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

    public boolean isContained(Task t, List<Task> listTask) {
        for (Task t2 : listTask) {
            if (t.getId() == (t2.getId())) {
                return true;
            }
        }
        return false;
    }

    public long returnDays(Task task){
        LocalDate deadline = task.getSluitdatum();
        LocalDate today = LocalDate.now();
        long elapsedDays = ChronoUnit.DAYS.between(today, deadline);
        return elapsedDays;
    }

        public List<Task> myTaskList(User sol) {
        List<Task> tasks = allTasks();
        List<Task> myTasks = vacatureService.getAllTasks(sol);
        List<Task> possibleTasks = new ArrayList<>();
        for (Task t : myTasks) {
            if (!doesContaine(t, tasks)) {
                possibleTasks.add(t);
            }
        }
        return possibleTasks;
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
