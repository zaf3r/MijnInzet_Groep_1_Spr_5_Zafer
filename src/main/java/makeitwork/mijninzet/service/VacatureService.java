package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
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


    public Task getTask(int taskId){
        return taskRepository.findTaskById(taskId);
    }

    //    lijst met vacatures van een Docent: zijn lijst met taken
    public List<Task> getAllTasks(User user){
        List<Task> tasks = new ArrayList<>();
        for (Task taskId: user.getTask()) {
            Task task = getTask(taskId.getId());
            tasks.add(task);
        }
        return tasks;
    }

    //save Vacature in DB SQL
    public void addTask(Task task, User user){
        task.getUsers().add(user);
        user.getTask().add(task);
        vacatureRepository.save(user);
        taskRepository.save(task);
    }
    //wordt een taak uit zijn eigen lijst verwijderd
//    public void removeTask(Task task, User user) {
//        System.out.println("Task: " + task);
//        user.removeApplication(task.getId());
//        vacatureRepository.save(user);
//    }

//    public User getUser(int userId){
//        return vacatureRepository.findUserById(userId);
//    }

    // voor de MANAGER: lijst met tasks en users die gesolliciteerd hebben (lijst sollicitanten)

    public List<Task> allApplications(Task task) {
        List<Task> allTasks = taskRepository.findAll();
        Iterator<Task> iter = allTasks.iterator();
        while (iter.hasNext()) {
            Task t = iter.next();
            if (t.getTaskStatus() == (Task.TaskStatus.APPROVED)) {
                iter.remove();}
            if (t.getUsers().isEmpty()) {
                    iter.remove();
                }
            }
        return allTasks;
    }

    //voor het zelfde doel als bovenstaande
    public List<User> getSollicitanten (int taskId){
        try{
            return taskRepository.findTaskById(taskId).getUsers();
        } catch (NullPointerException e){
            return new ArrayList<>();
        }
    }
//TODO is geen List<> denk ik
    public void approveSollicitant(User user, Task task){
        List<User> approved = new ArrayList<>();
        approved.add(user);
        taskRepository.findTaskById(task.getId()).setUsers(approved);
        task.setTaskStatus(Task.TaskStatus.APPROVED);
    }

//    public Task getApprovedTask(int taskId, int userId){
//        return taskRepository.findApprovedTaskById(taskId, userId);
//    }

    public void saveApprovedTasks(User user, Task task){
        user.getSollicitaties().add(task);
        System.out.println("Opgeslagen user is" + user);
        task.setUitvoerder(user);
        task.setTaskStatus(Task.TaskStatus.APPROVED);
        vacatureRepository.save(user);
        taskRepository.save(task);
    }







    //stel taak is komen te vervallen, moet deze taak overal uit de DB (bij teacher) verwijderd worden.
    public void removeTaskFromOverview(Task task){

//        for (User user: vacatureRepository.findAll()) {
//            removeTask(task,teacher);
//        }
        taskRepository.delete(task);
    }



}

