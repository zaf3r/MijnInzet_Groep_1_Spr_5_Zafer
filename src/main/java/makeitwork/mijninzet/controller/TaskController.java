package makeitwork.mijninzet.controller;

//
//@Controller
//@RequestMapping("/teacher")
//public class TaskController extends AbstractController {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private UserRepository usersRepository;
//
//    @Autowired
//    private SollicitatieRepository sollicitatieRepository;
//
//    @Autowired
//    VacatureService vacatureService;

//    @GetMapping("/taskOverview") //th:action
//    public String MenuHandler(Model model, Principal principal) {
//        User user = usersRepository.findByUsername(principal.getName());
//        List<Sollicitatie> sollicitaties = sollicitatieRepository.findByUser(user);
//        model.addAttribute("allTasks", tasks2React(sollicitaties));
//        return "taskOverview";
//    }

//    @GetMapping("/showTask/{task}")  //th:action
//    public String TaskDetailHandler(@RequestParam String taskId, Model model) {
////        String taakId = taak.getId();
//        Task taak = opening(taskId);
//        model.addAttribute("taak", taak);
//        model.addAttribute("deadline", returnDays(taak));
//        return "showTask"; //html
//    }
//
//    @GetMapping("/taskSave/{taskId}") //mapping bij voor de view
//    public String ApplicationHandler(@ModelAttribute("task") Task taak, @RequestParam("taskId") String taakId, Principal principal) {
//        taak = opening(taakId);
//        vacatureService.addTask(taak, usersRepository.findByUsername(principal.getName()));
//        return "redirect:/teacher/taskOverview";
//    }
//
//    @GetMapping("/myTasks")
//    public String MyTaskHandler(Model model, Principal principal) {
//        User user = usersRepository.findByUsername(principal.getName());
//        List<Sollicitatie> sollicitaties = sollicitatieRepository.findByUser(user);
//        model.addAttribute("myTasks", myTaskList(user));
//        return "myTasks";
//    }
//
//    @PostMapping("/taskDelete/{taskId}")
//    public String DeleteTaskHandler(@ModelAttribute("myTask") Task myTask, @RequestParam("taskId") String taakId, Principal principal){
//        myTask = opening(taakId);
//        System.out.println(myTask);
//        vacatureService.removeTask(myTask, usersRepository.findByUsername(principal.getName()));
//        return "redirect:/myTasks";
//    }

    //haalt alles uit database
//    private List<Task> allTasks() {
//        List<Task> tasks = this.taskRepository.findAll();
//        sortTasks(tasks);
//        return tasks;
//    }

//    private Task opening(String taskId) {
//        return this.taskRepository.findDocumentById(taskId);
//    }
//TODO
//    private List<Task> sortTasks(List<Task> tasks) {
//        Collections.sort(tasks, (a, b) -> {
//            return a.getTitel().compareTo(b.getTitel());
//        });
//        return tasks;
//    }

    //lijst met taken waar de docent nog op kan reageren
//    private List<Task> tasks2React(User  user) {
//        List<Task> tasks = allTasks();
//        System.out.println("All: " + tasks);
//        List<Task> myTasks = vacatureService.getAllTasks(user);
//        System.out.println("My tasks: " + myTasks);
//        List<Task> possibleTasks = new ArrayList<>();
//        for (Task t : tasks) {
//            if (!isContained(t, myTasks)) {
//                possibleTasks.add(t);
//            }
//        }
//        System.out.println(possibleTasks);
//        return possibleTasks;
//    }
//TODO
//    public List<Task> myTaskList(User sol) {
//        List<Task> tasks = allTasks();
//        List<Task> myTasks = vacatureService.getAllTasks(sol);
//        List<Task> possibleTasks = new ArrayList<>();
//        for (Task t : myTasks) {
//            if (!doesContaine(t, tasks)) {
//                possibleTasks.add(t);
//            }
//        }
//        return possibleTasks;
//    }

//    public boolean isContained(Task t, List<Task> listTask) {
//        for (Task t2 : listTask) {
//            if (t.getId().equals(t2.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//TODO
//    public boolean doesContaine(Task t, List<Task> listTask) {
//        for (Task t2 : listTask) {
//            if (t.getId() == (t2.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public long returnDays(Task task){
//        LocalDate deadline = task.getSluitdatum();
//        LocalDate today = LocalDate.now();
//        long elapsedDays = ChronoUnit.DAYS.between(today, deadline);
//        return elapsedDays;
//    }
//}

//        tasks.forEach((i)->myTasks.remove(i));
////         tasks.removeAll(myTasks); // levert tasks op waaruit alle eerder gereageerde taken zijn verwijderd
//        List<Task> diff = tasks.stream().filter(e -> !myTasks.contains(e)).collect(Collectors.toList());

