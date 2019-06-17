package makeitwork.mijninzet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
        user=userRepository.findByUsername(newUser.getUsername());
        var output = new BasicDBObject();
        if (user != null) {
            output.put("exists", true);
        } else {
            output.put("exists", false);
        }
        return output.toJson();
    }
    public User deSerializeUser(String requestPayload){
        ObjectMapper objectMapper=new ObjectMapper();
        User user = new User();
        try {
            user = objectMapper.readValue(requestPayload, User.class);
        }catch (IOException e){};
        user.setPassword(user.encryptPassword(user.getPassword()));
        return user;
    }
    @PostMapping("/allRoles")
    public @ResponseBody String allRoles(){
        List<Role> allRoles=roleRepository.findAll();
        var output=new BasicDBObject();
        for (Role rol:allRoles
             ) {var name= rol.getRoleName();
            System.out.printf("%s\n",name);
                output.put("name",name);
        }
        return output.toJson();
    }

}
