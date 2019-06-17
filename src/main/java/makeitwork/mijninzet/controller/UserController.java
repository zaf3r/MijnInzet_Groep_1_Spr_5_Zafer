package makeitwork.mijninzet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    public UserController() {
    }

    @PostMapping("/updateUser")
    public String updateUser(Model model){
        model.addAttribute("status","Voor uw gebruikersnaam en wachtwoord in");
        return "crudUser";
    }
    @PostMapping("/retrieveUser")
    public String retreiveUser(Model model){
        model.addAttribute("status","Uw gegevens zijn:");
        return "crudUser";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(Model model){
        model.addAttribute("status","Deze gebruiker is verwijderd");
        return "crudUser";
    }
    @PostMapping("/blockUser")
    public String blockUser(Model model){
        model.addAttribute("status","Deze gebruiker kan niet meer inloggen");
        return "crudUser";
    }
    @PostMapping("/checkUserName")
    public @ResponseBody String nameCorrect(@RequestBody String requestPayload){
        var input = BasicDBObject.parse(requestPayload);
        var username = input.getString("username");
        var output = new BasicDBObject();
        var user = userRepository.findByUsername(username);
        if (user != null) {
            output.put("exists", true);
        } else {
            output.put("exists", false);
        }
        return output.toJson();
    }
    @PostMapping("/checkEmail")
    public @ResponseBody String emailCorrect(@RequestBody String requestPayload){
        var input = BasicDBObject.parse(requestPayload);
        var email = input.getString("email");
        var output = new BasicDBObject();
        var eAdress = userRepository.findByEmail(email);
        if (eAdress != null) {
            output.put("exists", true);
        } else {
            output.put("exists", false);
        }
        return output.toJson();
    }
    @PostMapping("/newUser")
    public @ResponseBody String newUser(@RequestBody String requestPayload){
        User newUser=new User();
        newUser=deSerializeUser(requestPayload);
        var user=userRepository.save(newUser);
        var output = new BasicDBObject();
        if (user != null) {
            output.put("exists", true);
        } else {
            output.put("exists", false);
        }
        return output.toJson();
    }
    public User deSerializeUser(String requestPayload){
        ObjectMapper objectmapper = new ObjectMapper();
        User user = new User();
        try {
            user = objectmapper.readValue(requestPayload, User.class);
        } catch (Exception e){};
        user.setPassword(user.encryptPassword(user.getPassword()));
        return user;
    }

}
