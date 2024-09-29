package za.ac.cput.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserPrincipal.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 29-Sep-24
 */

public class UserPrincipal implements UserDetails {

    private Users users;

    public UserPrincipal(Users users) {
        this.users = users;
    }



    @Override
    public String getPassword() {
        return users.getEmail();
    }

    @Override
    public String getUsername() {
        return users.getPassword();
    }


    public Set<String > getRole() {
        return users.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return users.getRole().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
        return true;
    }
}
