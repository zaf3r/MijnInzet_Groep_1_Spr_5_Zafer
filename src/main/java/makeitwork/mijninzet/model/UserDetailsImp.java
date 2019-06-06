package makeitwork.mijninzet.model;

import makeitwork.mijninzet.repository.RoleRepository;
import makeitwork.mijninzet.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImp extends User implements UserDetails {

    final private int CONDITION_ACTIVE = 1;

    @Autowired
    UsersRepository usersRepository;

    RoleRepository roleRepository;

    @Autowired
    private User user;

    public UserDetailsImp( User user) {
    this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return
                getRole().stream().map(role -> new SimpleGrantedAuthority("Role_" + role.getRoleName())).
                collect(Collectors.toList());

//        List<GrantedAuthority> authorities = new ArrayList<>();

        // Create a user and inject it?
//        user.getRole().forEach(r -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r.getRoleName());
//            authorities.add(authority);
//        });
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive()==CONDITION_ACTIVE;
    }
}
