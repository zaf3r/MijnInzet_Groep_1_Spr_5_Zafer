package makeitwork.mijninzet.controller;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;

import java.security.Principal;
import java.util.List;

public interface RetrieveUserRole {

    public static final int TEACHERROLEID = 1;
    public static final int ADMIN=2;
    public static final int COORDINATOR=4;

    default Role retrieveRole(UserRepository userRepository, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        List<Role> roleList = user.getRole();
        Role role = roleList.get(0);
        return role;
    }
    default Boolean isCoordinator(UserRepository userRepository, Principal principal){
        return retrieveRole(userRepository,principal).getRoleId()==COORDINATOR;
    }
    default Boolean isAdmin(UserRepository userRepository, Principal principal){
        return retrieveRole(userRepository,principal).getRoleId()==ADMIN;
    }

    default Boolean isTeacher(UserRepository userRepository, Principal principal) {
        return retrieveRole(userRepository,principal).getRoleId()==TEACHERROLEID;
    }
}