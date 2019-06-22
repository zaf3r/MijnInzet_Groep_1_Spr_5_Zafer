package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class VacatureService {

    @Autowired
    private VacatureRepository vacatureRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

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

    public User getUser(int userId){
        return vacatureRepository.findUserById(userId);
    }

//    lijst met vacatures van een Docent: zijn lijst met taken
    public List<Task> getAllTasks(User user){
        List<Task> tasks = new ArrayList<>();
        for (String taskId: user.getTasks()) {
            Task task = getTask(taskId);
            tasks.add(task);
        }
        return tasks;
    }

    // voor de MANAGER: lijst met tasks en users die gesolliciteerd hebben (lijst sollicitanten)
    public List<User> getAllUsers(Task task) {
        List<User> sollicitanten = new ArrayList<>();
        sollicitanten = vacatureRepository.findAll();
        Iterator<User> iter = sollicitanten.iterator();
        while (iter.hasNext()) {
            User user = iter.next();
            if (!user.getTaskIds().contains(task)) {
                iter.remove();
            }
            System.out.println("*******************" + sollicitanten);
        }
        return sollicitanten;
    }

//    public List<Task> getAllTasks(User user){
//        List<Task> sollicitaten = new ArrayList<>();
//        sollicitaten = user.getTask();
//        Iterator<Task> iter = sollicitaten.iterator();
//        while (iter.hasNext()){
//            Task task = iter.next();
//            if(!user.getTaskIds().contains(user)) {
//                iter.remove();
//            }
//            System.out.println("************" + sollicitaten);
//        }
//        return sollicitaten;
//    }


//        System.out.println(sollicitanten);
//        for (User user: sollicitanten) {
//            if(!user.getTaskIds().contains(task)) {
//               sollicitanten.remove(user);
//            }
//            System.out.println( "*******************" + sollicitanten);
//        }
//        return sollicitanten;
//
//    }

    //stel taak is komen te vervallen, moet deze taak overal uit de DB (bij teacher) verwijderd worden.
    public void removeTaskFromOverview(Task task){

//        for (User user: vacatureRepository.findAll()) {
//            removeTask(task,teacher);
//        }
        taskRepository.delete(task);
    }



}

