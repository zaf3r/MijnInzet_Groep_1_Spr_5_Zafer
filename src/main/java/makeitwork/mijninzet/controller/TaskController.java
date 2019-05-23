package makeitwork.mijninzet.controller;


import makeitwork.mijninzet.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import makeitwork.mijninzet.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
//@RequestMapping(value = "/task")
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/taskOverview") //th:action
    public String MenuHandler(Model model){
        List<Task> tasks = this.taskRepository.findAll();
        model.addAttribute("allTasks", tasks);
        return "taskOverview";
    }

    @GetMapping("/showTask/task")  //th:action
    public String TaskOverviewHandler(@PathVariable String id, Model model) {
        String docNr = id;
        Task task = taskRepository.findDocumentById(docNr);
        model.addAttribute("task", task);
        return "showTask"; //html
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
