package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController implements RetrieveUserRole {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SubjectRepository subRepo;

    @RequestMapping(value="/{object}", method=RequestMethod.GET)
    public String addSubject(@PathVariable String object, Model model, Principal principal) {
        switch (object) {
            case "vak": {
                Subject subject = new Subject();
                model.addAttribute("attr3", subject);
                model.addAttribute("subjects", getSubjectList());
                Role role = retrieveRole(userRepository,principal);
                model.addAttribute("roleUser", role);

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
        model.addAttribute("hours", subject.getHours());
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
