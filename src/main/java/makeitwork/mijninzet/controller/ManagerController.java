package makeitwork.mijninzet.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/manager", method = RequestMethod.GET)
public class ManagerController {


    @Autowired
    private KnowledgeAreaRepository areaRepository;

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
                model.addAttribute("KnowledgeAreas", getKnowledgeAreaList());
                return ("addSubject");
            }
            //in knowledgeAreaController
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
        List<KnowledgeArea> allKnowledgeAreas = areaRepository.findAllByOrderByKnowledgeAreaIdAsc();
        System.out.println(allKnowledgeAreas.size() + "===================");
        return allKnowledgeAreas;
    }

    //in Subject controller
//    @RequestMapping(value = "saveSubject", method = RequestMethod.POST)
//    public String saveSubject(@ModelAttribute("saveSubject") Subject subject, Model model) {
//        model.addAttribute("subjectName", subject.getSubjectName());
//        subRepo.save(subject);
//        return "redirect:/manager/vak";
//    }

    @PostMapping("saveKnowledgeandCourse")
    public @ResponseBody Subject SaveSubjectHandler(@RequestBody Subject data, @RequestParam("naam") String naam){
        System.out.printf("inhoud requestPayload = %s\n",data);

        Subject ret = data;
        // Haalt de naam niet op van het kennisgebied
        //nog zorgen dat een arraytlsit wordt meegegeven client kant naar back-end
        // kan ook de info terug weglaten
        var output = new BasicDBObject();
        try {
//            Subject newSubject = mapper.readValue(data, Subject.class);
            System.out.println("new subject is: " + data);
            var subject = subRepo.save(data);

            ret = subject;

            if (subject != null) {
                output.put("exists", true);
            } else {
                output.put("exists", false);
            }
        } catch (Exception IO) {
            IO.printStackTrace();
            IO.getMessage();
        }
        return ret;
    }

    private Subject deSerializeUser(String requestPayload){
        ObjectMapper objectmapper = new ObjectMapper();
        Subject subject = new Subject();
        try {
            subject = objectmapper.readValue(requestPayload, Subject.class);
        } catch (Exception e){
            System.out.println("***********" + e.getMessage() + "***********");
        }
        return subject;
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

    //in knowledgeAreaController
    @RequestMapping(value = "saveKnowledgeArea", method = RequestMethod.POST)
    public String saveArea(@ModelAttribute("saveKnowledgeArea") KnowledgeArea area, Model model) {
        model.addAttribute("knowledgeArea", area.getKnowledgeArea());
        areaRepository.save(area);
        return "redirect:/manager/kennisgebied";
    }

    //in knowledgeAreaController
    @RequestMapping(value = "deleteKnowledgeArea", method = RequestMethod.POST)
    public String deleteKnowledgeArea(@ModelAttribute("deleteKnowledgeArea") KnowledgeArea area, Model model){
        int knowledgeAreaId = Integer.parseInt(area.getKnowledgeArea());
        KnowledgeArea deleteKnowledgeArea = areaRepository.findByKnowledgeAreaId(knowledgeAreaId);
        areaRepository.delete(deleteKnowledgeArea);

        return "redirect:/manager/kennisgebied";
    }

}


