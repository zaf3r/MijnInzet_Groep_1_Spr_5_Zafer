package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CourseRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController {


    @Autowired
    private SubjectRepository subRepo;

    @RequestMapping(value="/{object}", method=RequestMethod.GET)
    public String addSubject(@PathVariable String object, Model model) {
        switch (object) {
            case "vak": {
                Subject subject = new Subject();
                model.addAttribute("attr3", subject);
                model.addAttribute("subjects", getSubjectList());
                return ("addSubject");
            }
        }
        return "";
    }


    public List<Subject> getSubjectList() {
        List<Subject> allSubjects = subRepo.findAllByOrderBySubjectIdAsc();
        System.out.println(allSubjects.size() + "===================");
        return allSubjects;
    }

    @NotNull
    @RequestMapping(value = "saveSubject", method = RequestMethod.POST)
    public String saveSubject(@ModelAttribute("saveSubject") Subject subject, Model model) {
        try {
        model.addAttribute("subjectName", subject.getSubjectName());
        subRepo.save(subject);
    }
        catch (ConstraintViolationException ex) {
            throw new ValidationException( "Unable to determine current Hibernate session", ex );
        }
        finally {
            return "redirect:/manager/vak";
        }
    }

    //in Subject controller
    @RequestMapping(value = "deleteSubject", method = RequestMethod.POST)
    public String subjectDelete(@ModelAttribute("deleteSubject") Subject subject, Model model){
        int subjectid = Integer.parseInt(subject.getSubjectName());
        Subject deletedSubject = subRepo.findBySubjectId(subjectid);
        subRepo.delete(deletedSubject);
        return "redirect:/manager/vak";
    }

    public static boolean confirmPlease(boolean delete){
        return delete;
    }

}
