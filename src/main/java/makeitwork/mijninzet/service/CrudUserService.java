package makeitwork.mijninzet.service;

import makeitwork.mijninzet.controller.RetrieveUserRole;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CrudUserService implements RetrieveUserRole {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    Principal principal=new Principal() {
        @Override
        public String getName() {
            return null;
        }
    };

    User user;

    public String actualUserName(Principal principal){
        return userRepository.findByUsername(principal.getName()).getUsername();
    }
    public void storeUser(User user){ userRepository.save(user);}

    public void updateUser(User user,Principal principal) {
        User thatUser = new User();
        thatUser = null;
        thatUser = userRepository.findByUsername(user.getUsername());
        if (thatUser == null) thatUser = userRepository.findByEmail(user.getEmail());
        user.setId(thatUser.getId());
        if(user.getRole()==null) user.setRole(thatUser.getRole());
        storeUser(user);
    }
    public Boolean userBestaat(User user){
        Boolean bestaat=false;
        if (userRepository.findByUsername(user.getUsername())!=null) bestaat=true;
        if (userRepository.findByEmail(user.getEmail())!=null) bestaat=true;
        return bestaat;
    }
    public String UserName(Principal principal){
        //todo nullpointer oplossen
        return userRepository.findByUsername(principal.getName()).getUsername();
    }
    public String UserEmail(Principal principal){
        return userRepository.findByUsername(principal.getName()).getEmail();
    }
}
