package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;


@Controller
public class SubjectController {

    KnowledgeAreaRepository knowledgeAreaRepository;

//    @PostMapping("lijstVanKennisgebieden")
//    public String ListKnowledgeAreaHandler(@ModelAttribute("getListKnowledgeAreas") KnowledgeArea area, Model model){
//        int knowledgeAreaId = Integer.parseInt(area.getKnowledgeArea());
//        model.addAttribute("list", getKnowledgeAreaList());
//        return "redirect:/manager/vak";
//    }
//
//    public List<KnowledgeArea> getKnowledgeAreaList() {
//        List<KnowledgeArea> allKnowledgeAreas = knowledgeAreaRepository.findAllByOrderByKnowledgeAreaIdAsc();
//        return allKnowledgeAreas;
//    }
}
