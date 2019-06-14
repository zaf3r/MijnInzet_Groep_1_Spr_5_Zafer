package makeitwork.mijninzet.controller;


import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.TaskDTO;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.standard.Destination;
import java.util.*;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UsersRepository usersRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model){
        model.addAttribute("allTasks", tasks2Present());
        return "taskOverview";
    }
    @GetMapping("/showTask/{task}")  //th:action
    public String TaskDetailHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Model model) {
        taak=opening(taakId);
        model.addAttribute("taak",taak);
        return "showTask"; //html
    }

    private List<Task> tasks2Present(){
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
//todo TaskDTO vullen
//task ophalen


    private TaskDTO fillTaskDTO(Task task, User user, TaskDTO taskDTO){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(mapper ->{
            mapper(user-> user.getId)})
        modelMapper.addMappings(mapper -> {
                    mapper.map(src -> src.getBillingAddress().getStreet(),
                            Destination::setBillingStreet);

    }
//todo entity vullen

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
