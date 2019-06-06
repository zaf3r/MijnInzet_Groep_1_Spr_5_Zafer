package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractController {

    UsersRepository usersRepository;

    public User voegUserToe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        return usersRepository.findByUsername(currentUserName);
    }
}
