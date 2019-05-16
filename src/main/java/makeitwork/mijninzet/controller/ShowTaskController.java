package makeitwork.mijninzet.controller;


import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.repository.TaskRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ShowTaskController {

    private TaskRepository taskRepository;

    public ShowTaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/showTask/{task}")  //th:action
    public String TaskOverviewHandler(@PathVariable ("task") String id, Model model) {
        String docNr = id;
        Task task = this.taskRepository.findDocumentById(docNr);
        model.addAttribute("taak", task);
        return "showTask"; //html
    }

//    @PostMapping("/showTask/{titel}")
//    public String getDocByName(@PathVariable("titel") String naam, Model model){
//        Task task = this.taskRepository.findDocByName(naam);
//        model.addAttribute("titelSelect", task);
//        return "showTask/{titel}";
//    }













    }
//    private static void selectAllRecordByRecordNumber(DBCollection collection)
//    {
//        BasicDBObject whereQuery = new BasicDBObject();
//        whereQuery.put("id", 5cd6e100ca6767487e2bddc5 );
//        DBCursor cursor = collection.find(whereQuery);
//        while(cursor.hasNext()) {
//            System.out.println(cursor.next());
//        }
//    }

//    public String showTaskHandler(@PathVariable String object, Model model){
//
//
//    }


