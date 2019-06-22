//package makeitwork.mijninzet.controller;
//
//
//import com.google.gson.Gson;
//import com.mongodb.BasicDBObject;
//import makeitwork.mijninzet.model.Task;
//import makeitwork.mijninzet.repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class taskControllerRest {
//    @Autowired
//    TaskRepository taskRepository;
//
//    @PostMapping("/addTask")
//    public @ResponseBody String newUser(@RequestBody String requestPayload){
//        Task task=deSerializeTask(requestPayload);
//        System.out.printf("dit is de taak: %s",task);
//        taskRepository.save(task);
//        var newTask=taskRepository.findById(task.getId());
//        var output = new BasicDBObject();
//        if (newTask != null) {
//            output.put("exists", true);
//        } else {
//            output.put("exists", false);
//        }
//        return output.toJson();
//    }
//    public Task deSerializeTask(String requestPayload){
//        System.out.printf("\n\ndit is de json string: %s\n",requestPayload);
//        return new Gson().fromJson(requestPayload, Task.class);
//    }
//}
