package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;

import java.security.Principal;
import java.util.List;

public interface RetrieveUserRole {

    default Role retrieveRole(UserRepository userRepository, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        List<Role> roleList = user.getRole();
        Role role = roleList.get(0);
        return role;

    }
}