package makeitwork.mijninzet.controller;

import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubjectScheduleController {

    @Autowired
    KnowledgeAreaRepository knowledgeAreaRepository;

    @PostMapping("/supplyKnowledgearea")
    public @ResponseBody String supplyKnowledgeAreas() {
        var areas=knowledgeAreaRepository.findAll();
        areas.sort(KnowledgeArea::compareTo);


        var output = new BasicDBObject();
//        if (eAdress != null) {
//            output.put("exists", true);
//        } else {
//            output.put("exists", false);
//        }
        return output.toJson();
    }
}