package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Unused.Course;
import makeitwork.mijninzet.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController extends AbstractController {

    @Autowired
    CourseRepository courseRepository;

    List<Course> courseStore = new ArrayList<>();

    @PostMapping("/saveCourse")
    public ResponseEntity<Object> addCourse(@RequestBody Course course){
    return new ResponseEntity<Object>(course, HttpStatus.OK);
    }

//    @GetMapping("/getCourses")
//    public ResponseEntity<Object> getCourses(){
//    return new ResponseEntity<Object>(course, HttpStatus.OK);
//    }

}
