package makeitwork.mijninzet;

import makeitwork.mijninzet.model.Task;

import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.util.Scanner;

public class TaskInlezen {


    //todo lees txt bestand met tasks in
//    public Set<Task> tasks() throws Exception {
//        Set<Task> tasks=new TreeSet<>();
//        final String filename = "Tasks.txt";
//        final String filepath = "/";
//        final String beschrijving = "<beschrijving>";
//        final String lokatie = "<lokatie>";
//        final String titel = "<titel>";
//        final String uren = "<uren>";
//        String regel = "";
//        File file = new File(filepath + filename);
//        Scanner sc = new Scanner(file);
//        while (sc.hasNextLine()) {
//            regel = sc.nextLine();
//            if (regel.contains(titel)) {
//                Task task = new Task();
//                regel.replace(titel, "");
//                task.setTitel(regel);
//            }
//            if (regel.contains(lokatie)) {
//                regel.replace(lokatie, "");
//                task().setLocatie(regel);
//            }
//            if (regel.contains(beschrijving)) {
//                regel.replace(beschrijving, "");
//                task().setLocatie(regel);
//            }
//            if (regel.contains(uren)) {
//                regel.replace(uren, "");
//                task().setLocatie(regel);
//            }
//
//        }
//        return addTask(tasks,task);
//    }
//
//    public Set<Task> addTask(Set<Task> tasks,Task task){
//        tasks.add(task);
//        return tasks;
//    }
//}
}
