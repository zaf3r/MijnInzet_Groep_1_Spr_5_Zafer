package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import makeitwork.mijninzet.service.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.SortedSet;

@Controller
@RequestMapping("/manager")
public class SollicitatieController {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private VacatureRepository vacatureRepository;

    @Autowired
    VacatureService vacatureService;

    @GetMapping("/sollicitaties")
    public String SollicitatieHandler(Model model, Task task) {
        model.addAttribute("listSoltaties", vacatureService.getAllUsers(task));
        return "sollicitaties";
    }

//    @GetMapping("/showsol/{task}")
//    public String ShowSollicitieHandler(@ModelAttribute("user") Task taskuser, @RequestParam("id") int id, Model model) {
//        taskuser = show(id);
//        model.addAttribute("sol", user);
//        return "redirect: sollicitaties";
//    }

    private SortedSet<String> show(int id) {
        User user = vacatureRepository.findUserById(id);
        return user.getTasks();
    }



}
