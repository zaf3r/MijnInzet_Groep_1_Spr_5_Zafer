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
        for (Task taskId: user.getTasks()) {
            Task task = getTask(taskId.getId());
            tasks.add(task);
        }
        return tasks;
    }

    //save Vacature in DB SQL
    public void addTask(Task task, User user) {
        boolean solliciteren = checkHoursSolliciteren(task, user);
        if (solliciteren = true) {
        task.getUsers().add(user);
        user.getTasks().add(task);
        vacatureRepository.save(user);
        taskRepository.save(task);
    }
    }

    //wordt een taak uit zijn eigen lijst verwijderd
    public void removeTask(Task task, User user) {
        System.out.println("Task: " + task);
        user.getTasks().remove(task);
        task.getUsers().remove(user);
        vacatureRepository.save(user);
        taskRepository.save(task);
    }


    // voor de MANAGER: lijst met tasks en users die gesolliciteerd hebben (lijst sollicitanten)
    public List<Task> allApplications() {
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

    public void saveApprovedTasks(User user, Task task){
        user.getSollicitaties().add(task);
        System.out.println("Opgeslagen user is" + user);
        task.setUitvoerder(user);
        task.setTaskStatus(Task.TaskStatus.APPROVED);
        int hours = addHours(task, user);
        user.setHoursAllocated(hours);
        vacatureRepository.save(user);
    }

    //TODO de uren final maken ivm software maintanance
    public int hoursToApply (User user) {
        int allocatedHours = user.getHoursAllocated(); //5
        int contractHours = user.getHours(); //40
        int hourstoFillIn = contractHours - allocatedHours; //35
        return hourstoFillIn;
    }

    public int addHours (Task task, User user){
        int allocatedHours = user.getHoursAllocated(); //0
        int taskHoursPossible = task.getUren(); //4
        return Integer.sum(allocatedHours, taskHoursPossible); //4
    }

    public boolean checkHoursSolliciteren (Task task, User user) {
        int contractHours = user.getHours(); //40
        int allocatedHours = user.getHoursAllocated(); //0
        int taskHoursPossible = task.getUren(); //4
        if((allocatedHours + taskHoursPossible) <= contractHours) {
            return true;
        } else {
            return false;
        }
    }



}

