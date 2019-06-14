package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Course;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CourseRepository;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private KnowledgeAreaRepository areaRepo;

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
            case "kennisgebied": {
                KnowledgeArea area = new KnowledgeArea();
                model.addAttribute("attr2", area);
                model.addAttribute("KnowledgeAreas", getKnowledgeAreaList());
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

    public List<KnowledgeArea> getKnowledgeAreaList() {
        List<KnowledgeArea> allKnowledgeAreas = areaRepo.findAllByOrderByKnowledgeAreaIdAsc();
        System.out.println(allKnowledgeAreas.size() + "===================");
        return allKnowledgeAreas;
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


}
