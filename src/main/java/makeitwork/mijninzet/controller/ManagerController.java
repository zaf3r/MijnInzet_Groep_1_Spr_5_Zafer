package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Course;
import makeitwork.mijninzet.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController {

    @Autowired
    private CourseRepository courseRepo;

    @RequestMapping(value = "/manager/vak", method = RequestMethod.GET)
    public String addCourse(Model model) {
        Course course = new Course();
        model.addAttribute("attr1", course);
        return ("addCourse");
    }


//    @RequestMapping(value="/manager/{object}", method=RequestMethod.GET)
//    public String addCourse(@PathVariable String object, Model model) {
//        switch (object) {
//            case "vak": {
//                Course course = new Course();
//                model.addAttribute("attr1", course);
//                return ("addCourse");
//            }
//        }
//        return "";
//    }

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("attribuut") Course course, Model model) {
        model.addAttribute("courseName", course.getCourseName());
        model.addAttribute("knowledgeField", course.getKnowledgeField());
        model.addAttribute("cohortNumber", course.getCohortNumber());
        courseRepo.save(course);
        return "";
    }

    //ArrayList of all courses
//    @RequestMapping(value = "/coursesByCohort/{cohortNumber}", method = RequestMethod.GET)
//    public List<Course> getAllCourses(@PathVariable int cohortNumber) {
//    return courseRepo.findByCohortNumberLike(int cohortNumber);
//    }
}
