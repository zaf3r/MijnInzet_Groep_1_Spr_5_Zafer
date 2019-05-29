package makeitwork.mijninzet.controller;


import makeitwork.mijninzet.model.Task;

import makeitwork.mijninzet.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import makeitwork.mijninzet.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class TaskController {

    Principal principal;

    @Autowired
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model){
        model.addAttribute("allTasks", allTasks());
        return "taskOverview";
    }
    @GetMapping("/showTask/{task}")  //th:action
    public String TaskDetailHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Model model) {
        taak = opening(taakId);
        model.addAttribute("taak",taak);
        return "showTask"; //html
    }

//    @PostMapping()
//    public String ApplicationHandler(@ModelAttribute("task") Task task, @RequestParam("taskId") String taakId){
//        Teacher teacher = teacherRepository.findByUsername(principal.getName());
//
//
//    }

    private List<Task> allTasks(){//haalt alles uit database
//        alle taken uit database ophalen
        List<Task> tasks = this.taskRepository.findAll();
//        de taken verwijderen waar user eerder op reageerde
        tasks2React(tasks);
//        taken op alfabetische volgorde zetten
        sortTasks(tasks);
//        opgeschoonde lijst aan handler geven
        return tasks;
    }

    private Task opening(String taskId){
        return this.taskRepository.findDocumentById(taskId);
    }

    private List<Task> sortTasks(List<Task> tasks){
        Collections.sort(tasks,(a,b) -> {return a.getTitel().compareTo(b.getTitel());});
        return tasks;
    }

    //lijst met taken waar de docent nog op kan reageren
    private List<Task> tasks2React(List<Task> tasks){
        //todo taken waar <user> eerder op reageerde uit database halen
        Set<Task> reacted = new HashSet<>();
//        statement hieronder verder uitwerken
//        set<Tasks> reacted = this.reactedrepo.findbyUser(<currentUserName()>
        Set<Task> alleVacatures= new HashSet<>(tasks);
        alleVacatures.removeAll(reacted); // levert tasks op waaruit alle eerder gereageerde taken zijn verwijderd
        List<Task> tasks2Do =new ArrayList<>(alleVacatures);
        return tasks2Do;
    }

    private String currentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        } else return "";
    }
//
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
