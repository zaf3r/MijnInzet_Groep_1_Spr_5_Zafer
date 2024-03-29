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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class VacatureController implements RetrieveUserRole {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    VacatureService vacatureService;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    VacatureRepository vacatureRepository;

    @GetMapping("/taskOverview")
    public String MenuHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("allTasks", tasks2React(user));
        model.addAttribute("user", vacatureService.hoursToApply(user));
        Role role = retrieveRole(usersRepository, principal);
        model.addAttribute("roleUser", role);

        return "taskOverview";
    }

    @GetMapping("/showTask/{task}")
    public String TaskDetailHandler(@RequestParam int taskId, Model model, Principal principal) {
        Task taak = getTask(taskId);
        model.addAttribute("taak", taak);
        model.addAttribute("deadline", returnDays(taak));
        Role role = retrieveRole(usersRepository, principal);
        model.addAttribute("roleUser", role);
        return "showTask"; //html
    }

    @GetMapping("/taskSave/{taskId}")
    public String ApplicationHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") int taakId, Principal principal) {
        taak = getTask(taakId);
        vacatureService.addTask(taak, usersRepository.findByUsername(principal.getName()));
        return "redirect:/teacher/taskOverview";
    }

    @GetMapping("/myTasks")
    public String MyTaskHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        model.addAttribute("myTasks", vacatureService.getAllTasks(user));
        Role role = retrieveRole(usersRepository, principal);
        model.addAttribute("roleUser", role);
        return "myTasks";
    }

    @GetMapping("/myApplications")
    public String MyApplicationsHandler(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        List<Task> myApplications = user.getSollicitaties();
        model.addAttribute("takenToDo", myApplications);
        Role role = retrieveRole(usersRepository, principal);
        model.addAttribute("roleUser", role);
        return "myApprovedTasks";
    }



    @PostMapping("/taskDelete/{taskId}")
    public String DeleteTaskHandler(@ModelAttribute("myTask") Task myTask, @RequestParam("taskId") int taakId, Principal principal) {
        myTask = getTask(taakId);
        System.out.println(myTask);
        vacatureService.removeTask(myTask, usersRepository.findByUsername(principal.getName()));
        return "redirect:/teacher/myTasks";
    }

    public Task getTask(int id) {
        return taskRepository.findTaskById(id);
    }

    public List<Task> allTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public List<Task> allTasksOPEN(){
        List<Task> task = taskRepository.findAll();
        List<Task> open = new ArrayList<>();
        for (Task t: task){
            if (t.getUitvoerder() == null){
                open.add(t);
            }
        } return open;
    }

    private List<Task> tasks2React(User user) {
        List<Task> tasks = allTasksOPEN();
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

    public long returnDays(Task task) {
        LocalDate deadline = task.getSluitdatum();
        LocalDate today = LocalDate.now();
        long elapsedDays = ChronoUnit.DAYS.between(today, deadline);
        return elapsedDays;
    }

    public List<Task> myWantedTaskList(User sol) {
        List<Task> tasks = allTasks();
        List<Task> myTasks = sol.getTasks();
        System.out.println(myTasks);
        List<Task> possibleTasks = new ArrayList<>();
        for (Task t : myTasks) {
            if (!doesContaine(t, tasks)) {
                possibleTasks.add(t);
            }
        }
        System.out.println("zijn gewenste taken lijst is:" + possibleTasks);
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
