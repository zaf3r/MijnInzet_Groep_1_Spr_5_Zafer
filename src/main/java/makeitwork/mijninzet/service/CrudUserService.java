package makeitwork.mijninzet.service;

import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class crudUserService {
//todo class user aan attributen view aanpassen
    @Autowired
    UserRepository userRepository;

    User user;

    public crudUserService() {
    }

    public User askForUser(String userName){
        return userRepository.findByUsername(userName);
    }

    public String encryptPwd(String plainTextPwd){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plainTextPwd);
    }

    public void insertUserInDB(User user){
        userRepository.save(user);
    }
//    todo gebruiker wijzigen
    public void changeUser(User user){
        User org=userRepository.findByUsername(user.getUsername());
        //welke velden zijn er gewijzigd?
        //password encrypten
        insertUserInDB(user);
    }
//    todo als gebruiker password wijzigt uitloggen

    private void inActivedUser(User user){
        final int INACTIVE=0;
        final String PWD="1&Yrevb(87>?crDGf%";
        user.setPassword(PWD);
        user.setActive(INACTIVE);
        insertUserInDB(user);
    }

}
