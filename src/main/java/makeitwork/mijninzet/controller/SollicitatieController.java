//package makeitwork.mijninzet.controller;
//
//import makeitwork.mijninzet.model.Sollicitatie;
//import makeitwork.mijninzet.model.Task;
//import makeitwork.mijninzet.repository.TaskRepository;
//import makeitwork.mijninzet.repository.UserRepository;
//import makeitwork.mijninzet.repository.VacatureRepository;
//import makeitwork.mijninzet.service.VacatureService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/manager")
//public class SollicitatieController {
//
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private UserRepository usersRepository;
//
//    @Autowired
//    private VacatureRepository vacatureRepository;
//
//    @Autowired
//    VacatureService vacatureService;
//
//
//    @GetMapping("/sollicitaties")
//    public String SollicitatieHandler(Model model, Task task) {
//        model.addAttribute("listSoltaties", vacatureService.getAllUsers(task));
//        //model.addAttribute ( de status)
//        return "sollicitaties";
//    }
//
////    @PostMapping("/goedkeuring")
////    public String Handler (){
////        sollicitatie.setTaskStatus(TaskStatus.APPROVED);
////        System.out.println("**********" + sollicitatie);
////        return "redirect: /sollicitaties";
////    }
//
//
//
//
//
//
//}
