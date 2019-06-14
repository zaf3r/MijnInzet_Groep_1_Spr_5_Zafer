package makeitwork.mijninzet.controller;

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
            //in Subject controller
            case "vak": {
                Subject subject = new Subject();
                model.addAttribute("attr3", subject);
                model.addAttribute("subjects", getSubjectList());
                return ("addSubject");
            }
            //in managementController
            case "kennisgebied": {
                KnowledgeArea area = new KnowledgeArea();
                model.addAttribute("attr2", area);
                model.addAttribute("KnowledgeAreas", getKnowledgeAreaList());
                model.addAttribute("subjects", getSubjectList());
                return ("courseManagement");
            }
        }
        return "";
    }

//    //in Subject controller
//    @RequestMapping(value = "/kennisgebied/{subject}", method = RequestMethod.GET)
//    public String subjectListHandler(Model model){
//        model.addAttribute("subjects", getSubjectList());
//        return "courseManagement";
//    }

    //in Subject controller & in ManagementController
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

    //in Subject controller
    @RequestMapping(value = "saveSubject", method = RequestMethod.POST)
    public String saveSubject(@ModelAttribute("saveSubject") Subject subject, Model model) {
        model.addAttribute("subjectName", subject.getSubjectName());
        subRepo.save(subject);
        return "redirect:/manager/vak";
    }

    //in Subject controller
    @RequestMapping(value = "deleteSubject", method = RequestMethod.POST)
    public String subjectDelete(@ModelAttribute("deleteSubject") Subject subject, Model model){
        //model.addAttribute("subject", subject.getSubjectId());

        //if pleaseConfirm() = true -> deleteSubject Else -> redirect
        int subjectid = Integer.parseInt(subject.getSubjectName());
        Subject deletedSubject = subRepo.findBySubjectId(subjectid);
        subRepo.delete(deletedSubject);
//        if (pleaseConfirm().equals(true)) {
//            subRepo.delete(deletedSubject);
//        }
//        else {
//            return "redirect:/manager/vak";
//        }
        return "redirect:/manager/vak";
    }

    //in managementController
    @RequestMapping(value = "saveKnowledgeArea", method = RequestMethod.POST)
    public String saveArea(@ModelAttribute("saveKnowledgeArea") KnowledgeArea area, Model model) {
        model.addAttribute("knowledgeArea", area.getKnowledgeArea());
        areaRepo.save(area);
        return "redirect:/manager/kennisgebied";
    }

    //in knowledgeAreaController / courceManagement
//    @RequestMapping(value = "knowledgeArea", method = RequestMethod.POST)
//    public String deleteKnowledgeArea(@ModelAttribute("deleteKnowledgeArea") KnowledgeArea area, Model model){
//
//
//        int subjectid = Integer.parseInt(area.getKnowledgeArea());
//        //KnowledgeArea deleteKnowledgeArea = areaRepo.findByKnowledgeAreaId();
//        //areaRepo.delete(deleteKnowledgeArea);
//
//        return "redirect:/manager/kennisgebied";
//    }

}
