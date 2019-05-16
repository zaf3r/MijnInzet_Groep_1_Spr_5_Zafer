package makeitwork.mijninzet.model;

import java.util.List;
import java.util.Set;

public class PreferenceForm {

    private List<Integer> preferenceRating;

    private Set<Preference> preferenceSet;

    public PreferenceForm(){
    }

    public PreferenceForm(List<Integer> preferenceRating) {
        this.preferenceRating = preferenceRating;
    }

    public List<Integer> getPreferenceRating() {
        return preferenceRating;
    }

    public void setPreferenceRating(List<Integer> preferenceRating) {
        this.preferenceRating = preferenceRating;
    }

    public Set<Preference> getPreferenceSet() {
        return preferenceSet;
    }

    public void setPreferenceSet(Set<Preference> preferenceSet) {
        this.preferenceSet = preferenceSet;
    }
}
