package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Cohort;
import makeitwork.mijninzet.model.KnowledgeArea;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CohortService {
    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;
    @Autowired
    private CohortRepository cohortRepository;

    public void addKnowledgeArea(Cohort cohort, KnowledgeArea knowledgeArea){
        cohort.addKnowledgeArea(knowledgeArea.getKnowledgeAreaId());
        cohortRepository.save(cohort);
    }
}
