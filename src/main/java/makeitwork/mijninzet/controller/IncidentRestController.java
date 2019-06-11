package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IncidentRestController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/allusers")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/findUser/{username}")
    public User findUser(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

}
