package makeitwork.mijninzet.MongoDB;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DBSeeder implements CommandLineRunner {

    private TaskRepository taskRepository;

    public DBSeeder(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    public void run(String... strings) throws Exception{

//        Task myFirstTask = new Task(
//                "Bibliothecaris",
//                "HvA",
//                "Het bijhouden van het archief, zetten van koffie, bijhouden van het register en " +
//                        "lezen van boeken",
//                10
//
//        );
//
//        Task mySecondTask = new Task(
//                "Oppasser",
//                "HvA",
//                "Tieners blijven kinderen, dus het is gewenst dat gedurende de openingstijden van de " +
//                        "HvA er iemand op deze studenten past.",
//                10
//
//        );

//        this.taskRepository.deleteAll();
//
//        this.taskRepository.save(myFirstTask);
//
//        this.taskRepository.save(mySecondTask);
        }




}
