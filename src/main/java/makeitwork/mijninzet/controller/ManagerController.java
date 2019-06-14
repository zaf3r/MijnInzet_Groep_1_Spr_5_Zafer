package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CourseRepository;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private KnowledgeAreaRepository areaRepo;

    @Autowired
    private SubjectRepository subRepo;

//    @RequestMapping(value = "/vak", method = RequestMethod.GET)
//    public String addCourse(Model model) {
//        Course course = new Course();
//        model.addAttribute("attr1", course);
//        return ("addCourse");
//    }


    @RequestMapping(value="/{object}", method=RequestMethod.GET)
    public String addSubject(@PathVariable String object, Model model) {
        switch (object) {
            case "vak": {
                Subject subject = new Subject();
                model.addAttribute("attr3", subject);
                model.addAttribute("subjects", getSubjectList());
                return ("addSubject");
            }
            case "kennisgebied": {
//                List<KnowledgeArea> areaList = areaRepo.findAll();
//                List<Subject> subjectList = subRepo.findAll();
                KnowledgeArea area = new KnowledgeArea();
                model.addAttribute("attr2", area);
                model.addAttribute("subjects", getSubjectList());
//                model.addAttribute("attr3", newSub);
                return ("courseManagement");
            }
        }
        return "";
    }

    @RequestMapping(value = "/kennisgebied/{subject}", method = RequestMethod.GET)
    public String subjectListHandler(Model model){
        model.addAttribute("subjects", getSubjectList());
        return "courseManagement";
    }

    public List<Subject> getSubjectList() {
        List<Subject> allSubjects = subRepo.findAllByOrderBySubjectIdAsc();
        System.out.println(allSubjects.size() + "===================");
        return allSubjects;
    }

    @RequestMapping(value = "saveSubject", method = RequestMethod.POST)
    public String saveSubject(@ModelAttribute("saveSubject") Subject subject, Model model) {
        model.addAttribute("subjectName", subject.getSubjectName());
        subRepo.save(subject);
        return "redirect:/manager/vak";
    }

    @RequestMapping(value = "deleteSubject", method = RequestMethod.POST)
    public String subjectDelete(@ModelAttribute("deleteSubject") Subject subject, Model model){
        model.addAttribute("subject", subject.getSubjectId());
        subRepo.delete(subject);
        return "redirect:/manager/vak";
    }

    @RequestMapping(value = "saveKnowledgeArea", method = RequestMethod.POST)
    public String saveArea(@ModelAttribute("saveKnowledgeArea") KnowledgeArea area, Model model) {
        model.addAttribute("knowledgeArea", area.getKnowledgeArea());
        areaRepo.save(area);
        return "redirect:/manager/kennisgebied";
    }

//    @RequestMapping(value = "saveSubject", method = RequestMethod.POST)
//    public String saveSubject(@ModelAttribute("saveSubject") Subject newSub, Model model) {
//        model.addAttribute("subject", newSub.getSubjectName());
//        subRepo.save(newSub);
//        return "redirect:/manager/courseManagement";
//    }

    @GetMapping(value ="/kennisgebied/{ka}")
    public String VakkenToevoegenKennisHandler(@RequestParam("kaId") String kaId, Subject subject, BindingResult bindingResult,
                                               Model model, @ModelAttribute("ka") KnowledgeArea ka) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("no Errors", true);
        }

            model.addAttribute("choosenSubject", subject.getSubjectId());
            model.addAttribute("knowledgeAreaID", ka.getKnowledgeArea());
            addSubject(ka, subject);
            return "redirect: manager/coursemanagement";
        }


    public void addSubject(KnowledgeArea ka, Subject subject){
        ka.getSubjects();
        System.out.println(ka);
        for (int i = 0; i < ka.getSubjects().size(); i++) {
            ka.getSubjects().add(subject);
            areaRepo.save(ka);
            System.out.println(ka);
        }
    }


}
