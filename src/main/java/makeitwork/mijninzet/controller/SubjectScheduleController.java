package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubjectScheduleController {

    @Autowired
    KnowledgeAreaRepository knowledgeAreaRepository;

    @PostMapping("/supplyKnowledgearea")
    public @ResponseBody String supplyKnowledgeAreas() {
//        alle kennisgebieden worden opgehaald en gesorteerd
        var areas=knowledgeAreaRepository.findAll();
        areas.sort(KnowledgeArea::compareTo);
        List<String> areaNames=new ArrayList<>();
        for (KnowledgeArea area:areas
             ) {areaNames.add(area.getKnowledgeArea());
        }
        String payload=new Gson().toJson(areaNames);
        return payload;
    }
}