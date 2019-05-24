package makeitwork.mijninzet.controller;


import makeitwork.mijninzet.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import makeitwork.mijninzet.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.naturalOrder;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model){
        List<Task> tasks = this.taskRepository.findAll();
        Collections.sort(tasks,(a,b) -> {return a.getTitel().compareTo(b.getTitel());});
        model.addAttribute("allTasks", tasks);
        return "taskOverview";
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
