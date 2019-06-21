package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.Temp;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserController() {
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
        User user = new Gson().fromJson(requestPayload, User.class);
        user.setPassword(user.encryptPassword(user.getPassword()));
        return user;
    }
    public Temp deSerializeTemp(String requestPayload){
        return new Gson().fromJson(requestPayload, Temp.class);
    }
    @PostMapping("/allRoles")
    public @ResponseBody String allRoles(@RequestBody String requestPayload){
        List<Role> allRoles=roleRepository.findAll();
        Gson output= new Gson();
        return output.toJson(allRoles);
    }
    @PostMapping("/newUserRole")
    public @ResponseBody String userRole(@RequestBody String requestPayload) {
        Temp temp=deSerializeTemp(requestPayload);

        List<Role> roles=new ArrayList<>();
        Optional<Role> role= roleRepository.findById(temp.getRoleId());
        Role addRole=role.get();
        roles.add(addRole);

        User user=new User();
        user=userRepository.findByUsername(temp.getUserName());
        System.out.printf("\n\nvers uit DB de user heeft de volgende rollen: %s\n",user.getRole());
        user.setRole(roles);
        System.out.printf("\n\nde user heeft nu de volgende rollen: %s\n",user.getRole());
        updateUser(user);
        User latestUser=new User();
        latestUser=userRepository.findByUsername(user.getUsername());
        System.out.printf("\n\nde user heeft na verwerken in db de volgende rollen: %s\n",latestUser.getRole());
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
        thatUser= userRepository.findByUsername(user.getUsername());
        if (thatUser==null) thatUser= userRepository.findByEmail(user.getEmail());
        user.setId(thatUser.getId());
        storeUser(user);
    }
    private void storeUser(User user){
        userRepository.save(user);
        userRepository.flush();
    }
}



