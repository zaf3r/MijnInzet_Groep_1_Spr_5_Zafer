package makeitwork.mijninzet.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.Temp;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.UserRepository;
import makeitwork.mijninzet.service.CrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements RetrieveUserRole {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CrudUserService crudUserService;

    Principal principal = new Principal() {
        @Override
        public String getName() {
            return null;
        }
    };


    @PostMapping("/checkUserName")
    public @ResponseBody
    String nameCorrect(@RequestBody String requestPayload) {
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
    public @ResponseBody
    String emailCorrect(@RequestBody String requestPayload) {
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
    public @ResponseBody
    String newUser(@RequestBody String requestPayload) {
        User newUser = new User();
        newUser = deSerializeUser(requestPayload);
        if (crudUserService.userBestaat(newUser) == true) crudUserService.updateUser(newUser,principal);
        else {
            crudUserService.storeUser(newUser);
        }
        var user = userRepository.findByUsername(newUser.getUsername());
        var output = new BasicDBObject();
        if (user != null) {
            output.put("exists", true);
        } else {
            output.put("exists", false);
        }
        return output.toJson();
    }

    public User deSerializeUser(String requestPayload) {
        User user = new Gson().fromJson(requestPayload, User.class);
        user.setPassword(user.encryptPassword(user.getPassword()));
        return user;
    }

    public Temp deSerializeTemp(String requestPayload) {
        return new Gson().fromJson(requestPayload, Temp.class);
    }

    @PostMapping("/allRoles")
    public @ResponseBody
    String allRoles(@RequestBody String requestPayload) {
        List<Role> allRoles = roleRepository.findAll();
        Gson output = new Gson();
        return output.toJson(allRoles);
    }

    @PostMapping("/newUserRole")
    public @ResponseBody
    String userRole(@RequestBody String requestPayload) {
        Temp temp = deSerializeTemp(requestPayload);
        if (temp.getRoleId() > 0) {
            List<Role> roles = new ArrayList<>();
            Optional<Role> role = roleRepository.findById(temp.getRoleId());
            roles.add(role.get());
            User user = userRepository.findByUsername(temp.getUserName());
            user.setRole(roles);
            crudUserService.updateUser(user,principal);
        }
        return "crudUser";
    }

    @PostMapping("/checkIsCoordinator")
    public @ResponseBody
    String roleActualUser(@RequestBody String requestPayload, Principal principal) {
        var output = new BasicDBObject();
        if (isCoordinator(userRepository, principal) == true) {
            output.put("isCoordinator", true);
        } else {
            output.put("isCoordinator", false);
        }
        return output.toJson();
    }

    @PostMapping("/actualUserName")
    public @ResponseBody
    String actualUserName(@RequestBody String requestPayload, Principal principal) {
        return new Gson().toJson(crudUserService.actualUserName(principal));

//        @PostMapping("/checkIsTeacher")
//    public @ResponseBody String roleTeacherUser(@RequestBody String requestPayload) {
//        var output = new BasicDBObject();
//        if (isTeacher(userRepository,principal) ==true) {
//            output.put("isTeacher", true);
//        } else {
//            output.put("isTeacher", false);
//        }
//        return output.toJson();
    }

    private Boolean userBestaat(User user){
        Boolean bestaat=false;
        if (userRepository.findByUsername(user.getUsername())!=null) bestaat=true;
        if (userRepository.findByEmail(user.getEmail())!=null) bestaat=true;
        return bestaat;
    }

    @PostMapping("/actualEmail")
    public @ResponseBody
    String actualUserEmail(@RequestBody String requestPayload, Principal principal) {
        return new Gson().toJson(crudUserService.UserEmail(principal));
    }
}



