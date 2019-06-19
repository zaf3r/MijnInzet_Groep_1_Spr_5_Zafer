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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        System.out.printf("\n\nnew user:  %s\n\n",newUser);
        if (userBestaat(newUser)==true) updateUser(newUser); else {storeUser(newUser);}
        var user=userRepository.findByUsername(newUser.getUsername());
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
    public @ResponseBody String allRoles(@RequestBody String requestPayload){
        List<Role> allRoles=roleRepository.findAll();
        Gson output= new Gson();
        return output.toJson(allRoles);
    }
    @PostMapping("/newUserRole")
    public @ResponseBody String userRole(@RequestBody String requestPayload) {
        String[] items = requestPayload.split(",");
        for (var i = 0; i < items.length; i++) {
            var word = items[i];
            var index = word.indexOf(":");
            items[i] = word.substring(index+2);
            word=items[i];
            items[i]=word.replace("}","");
            word=items[i];
            items[i]=word.replace("\"","");
        }
//        Optional<Role> role= roleRepository.findById(Integer.parseInt(items[1]));
//        User user=userRepository.findByUsername(items[0]);
////        List<Role> roles=user.getRole();
////        roles.add(role.get());
////        user.setRole(roles);
//        userRepository.save(user);
//        System.out.printf("%s",user);
        return "crudUser";
    }
    private Boolean userBestaat(User user){
        Boolean bestaat=false;
        if (userRepository.findByUsername(user.getUsername())!=null) bestaat=true;
        if (userRepository.findByEmail(user.getEmail())!=null) bestaat=true;
        return bestaat;
    }
    private void updateUser(User user){
        User thatUser=new User();
        thatUser=null;
        System.out.printf("\n\nuser:  %s\n\n",user);
        thatUser= userRepository.findByUsername(user.getUsername());
        if (thatUser==null) thatUser= userRepository.findByUsername(user.getEmail());
        userRepository.deleteById(thatUser.getId());
        thatUser.setEmail(user.getEmail());
        thatUser.setPassword(user.getPassword());
        thatUser.setFamilyName(user.getFamilyName());
        thatUser.setNamePrefix(user.getNamePrefix());
        thatUser.setSurname(user.getSurname());
        thatUser.setActive(user.getActive());
        thatUser.setUsername(user.getUsername());
        System.out.printf("\n\nthatUser:  %s\n\n",thatUser);
        storeUser(thatUser);
    }
    private void storeUser(User user){
        userRepository.save(user);
    }
}

