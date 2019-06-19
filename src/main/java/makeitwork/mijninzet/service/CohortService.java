package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.Cohort;

import makeitwork.mijninzet.model.Task;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.CohortRepository;
import makeitwork.mijninzet.repository.KnowledgeAreaRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CohortService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CohortRepository cohortRepository;

    public Subject getSubject(String subjectName){
        return subjectRepository.findBySubjectName(subjectName);
    }


    public List<Subject> getAllSubjects(Cohort cohort){
        List<Subject> subjects = new ArrayList<>();
        for (String subjectName: cohort.getSubjectNames()) {
            Subject subject = getSubject(subjectName);
            subjects.add(subject);
        }
        return subjects;
    }

    public void addSubject(Cohort cohort, Subject subject){
        cohort.addSubject(subject.getSubjectName());
        cohortRepository.save(cohort);
    }

    public void removeSubject(Cohort cohort, Subject subject){
        cohort.removeSubject(subject.getSubjectName());
        cohortRepository.save(cohort);
    }
}
