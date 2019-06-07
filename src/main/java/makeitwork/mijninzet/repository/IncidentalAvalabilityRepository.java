package makeitwork.mijninzet.repository;

import makeitwork.mijninzet.model.IncidentalAvalability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentalAvalabilityRepository extends JpaRepository <IncidentalAvalability, Integer> {

    //this function saves one single incident
    //IncidentalAvalability save(S dag);
}
