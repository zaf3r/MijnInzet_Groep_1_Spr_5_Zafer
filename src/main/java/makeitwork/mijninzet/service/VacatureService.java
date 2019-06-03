package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.Teacher;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.TaskRepository;
import makeitwork.mijninzet.repository.UsersRepository;
import makeitwork.mijninzet.repository.VacatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;
import java.util.TreeSet;

@Service
public class VacatureService {

    @Autowired
    private VacatureRepository vacatureRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UsersRepository usersRepository;

    //save Vacature in DB SQL
    public void addTask(Task task, Teacher teacher){
        teacher.getId();
//        if (vacatureRepository.existsById(teacher.getId()))
//        {
//            teacher=vacatureRepository.getOne(teacher.getId());
//        }
        teacher.addTask(task.getId());
        vacatureRepository.save(teacher);
    }

    //wordt een taak uit zijn eigen lijst verwijderd
    public void removeTask(Task task, User user) {
        Teacher teacher = new Teacher();
        teacher = (Teacher) user;
        if (vacatureRepository.existsById(teacher.getId())) {
            teacher = vacatureRepository.getOne(teacher.getId());
            teacher.removeTask(task.getId());
        }
        vacatureRepository.save(teacher);
    }
    //MongoDB
    public Task getTask(String taskId){
        return taskRepository.findDocumentById(taskId);
    }

    //lijst met vacatures van een Docent: zijn lijst met taken
    public Set<Task> getAllTasks(Teacher teacher){
        Set<Task> tasks=new TreeSet<>();
        for (String taskId: teacher.getTasks()
        ) {Task task=new Task();
            task=getTask(taskId);
            tasks.add(task);
        }
        return tasks;
    }

    //stel taak is komen te vervallen, moet deze taak overal uit de DB (bij teacher) verwijderd worden.
    public void removeTaskFromOverview(Task task){
        for (Teacher teacher: vacatureRepository.findAll()) {
            removeTask(task,teacher);
        }
        taskRepository.delete(task);
    }
}

