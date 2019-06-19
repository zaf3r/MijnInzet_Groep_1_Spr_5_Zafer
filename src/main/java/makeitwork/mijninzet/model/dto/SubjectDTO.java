package makeitwork.mijninzet.model.dto;

import makeitwork.mijninzet.controller.AbstractController;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;


import java.time.LocalDate;

public class SubjectDTO extends AbstractController {

    KnowledgeAreaRepository knowledgeAreaRepository;

    public void addTask(Subject task, KnowledgeArea user){
        //willen KA toevoegen aan vak
        task.addKnowledgeToSubject(user.getKnowledgeAreaId());
        knowledgeAreaRepository.save(user);
    }
}

