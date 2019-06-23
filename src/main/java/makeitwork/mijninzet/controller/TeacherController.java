package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.*;
import makeitwork.mijninzet.model.preference.Preference;
import makeitwork.mijninzet.model.preference.PreferenceForm;
import makeitwork.mijninzet.model.preference.PreferenceScale;
import makeitwork.mijninzet.model.preference.Subject;
import makeitwork.mijninzet.repository.PreferenceRepository;
import makeitwork.mijninzet.repository.SubjectRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController implements RetrieveUserRole{

    final private List<PreferenceScale> preferenceScaleList = getPreferenceScaleList();

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @GetMapping("preference")
    public String addPreferences(Model model, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        Role role = retrieveRole(userRepository,principal);

        Set<Preference> preferenceSet = findCurrentUserPreference(user);

        List<Subject> subjectList = subjectRepository.findAll();

        if(preferenceSet.isEmpty()) {
            preferenceSet = emptyPreferenceStarter(subjectList, user);
        }

        // Preparing preference Form
        List<Integer> preferenceRatingList = new ArrayList<>();
        PreferenceForm preferenceForm = new PreferenceForm(preferenceRatingList);

        //Loading the preference form with data - FIX THIS PART
        for (Preference preference: preferenceSet) {
            preferenceForm.getPreferenceRating().add(preference.getPreference());
        }

        //Loading model attributes
        model.addAttribute("roleUser", role);
        model.addAttribute("preferenceForm",preferenceForm);
        model.addAttribute("subjectsList",subjectList);
        model.addAttribute("preferenceScaleList",preferenceScaleList);

        return "voorkeur-vakken";
    }


    @PostMapping("postPreferences")
    public String postPreferences(@ModelAttribute("preferenceForm") PreferenceForm preferenceForm,
                                  Principal principal) {

        //loading in user
        User user = userRepository.findByUsername(principal.getName());

        //loading preference
        Set<Preference> preferenceSet = preferenceRepository.findAllByUser(user);

        //Overwrite values from form
        int index = 0;
        for (Preference preference : preferenceSet) {
                int tempRating = preferenceForm.getPreferenceRating().get(index++);
                preference.setPreference(tempRating);
        }

         //Save preferences
        preferenceRepository.saveAll(preferenceSet);

        return "redirect:/teacher/preference";
    }

    /**
     * Method for retrieving an arraylist of preference scale values
     * These values are used in "voorkeur-vakken" for generating the radio buttons
     * This is seperated so that it can always be changed in case scales change
     * @return
     */
    private List<PreferenceScale> getPreferenceScaleList() {
        List<PreferenceScale> preferenceScaleList = new ArrayList<>();

        preferenceScaleList.add(new PreferenceScale(1));
        preferenceScaleList.add(new PreferenceScale(2));
        preferenceScaleList.add(new PreferenceScale(3));

        return preferenceScaleList;
    }

    /**
     * Method returns user preferences based on user of current session, provided in controller
     * @param user current user
     * @return Set of Preference object of user
     */
    public Set<Preference> findCurrentUserPreference(User user) {
        Set<Preference> preferenceSet = preferenceRepository.findAllByUser(user);
        return preferenceSet;
    }

    /**
     * In case the user has no prior preferences, initial values are set in databases.
     * @param subjectList List of subjects retrieved from mysql
     * @param user current session user
     */
    public Set<Preference> emptyPreferenceStarter(List<Subject> subjectList, User user)  {
        Set<Preference> preferenceSet = new HashSet<>();

        //Iterate over each subject for inserting info in Mysql
        for (Subject subject: subjectList) {
            Preference tempPreference = new Preference();
            tempPreference.setSubject(subject);
            tempPreference.setUser(user);
            preferenceSet.add(tempPreference);
        }
        preferenceRepository.saveAll(preferenceSet);
        return preferenceSet;
    }

    public List<Preference> geneRateInputForm(User user) {

        // Prepare subjects, Set object to store Preference , and index
        List<Preference> preferenceSet = new ArrayList<>();
        List<Subject> subjectList = subjectRepository.findAll();
        int index = 0;

        // Create a preference object for each subject
        for (Subject subject : subjectList) {
            Preference tempPreference = new Preference();
            tempPreference.setSubject(subject);
            tempPreference.setUser(user);
            index++;
            // Within each subject, set the value of the preference
            for (PreferenceScale preferenceScale : preferenceScaleList) {
                tempPreference.setPreference(preferenceScale.getScale());
                preferenceSet.add(tempPreference);
            }
        }
        return preferenceSet;
    }

}
