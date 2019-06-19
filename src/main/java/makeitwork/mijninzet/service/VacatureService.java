package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VacatureService {

    @Autowired
    private VacatureRepository vacatureRepository;
    @Autowired
    private TaskRepository taskRepository;

    //save Vacature in DB SQL
    public void addTask(Task task, User user){
        System.out.println("Task: " + task);
        System.out.println("User: " + user);
        System.out.println("User: " + user.getUsername());

        user.addTask(task.getId());
        vacatureRepository.save(user);
    }
    //todo teller bijhouden??
    //wordt een taak uit zijn eigen lijst verwijderd
    public void removeTask(Task task, User user) {
        System.out.println("Task: " + task);
        user.removeTask(task.getId());
        vacatureRepository.save(user);
    }
    //MongoDB
    public Task getTask(String taskId){
        return taskRepository.findDocumentById(taskId);
    }

    //lijst met vacatures van een Docent: zijn lijst met taken
    public List<Task> getAllTasks(User user){
        List<Task> tasks = new ArrayList<>();
        for (String taskId: user.getTasks()
        ) { Task task = getTask(taskId);
            tasks.add(task);
        }
        return tasks;
    }

    //stel taak is komen te vervallen, moet deze taak overal uit de DB (bij teacher) verwijderd worden.
    public void removeTaskFromOverview(Task task){

//        for (User user: vacatureRepository.findAll()) {
//            removeTask(task,teacher);
//        }
        taskRepository.delete(task);
    }



}

