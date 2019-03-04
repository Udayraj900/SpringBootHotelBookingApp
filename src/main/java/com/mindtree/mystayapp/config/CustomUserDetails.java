package com.mindtree.mystayapp.config;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mindtree.mystayapp.model.Role;
import com.mindtree.mystayapp.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides a basic implementation of the UserDetails interface
 */
public class CustomUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    public CustomUserDetails(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.authorities = translate(user.getRole());
    }

    /**
     * Translates the List<Role> to a List<GrantedAuthority>
     * @param roles the input list of roles.
     * @return a list of granted authorities
     */
    private Collection<? extends GrantedAuthority> translate(Role roles) {
        List<GrantedAuthority> authority = new ArrayList<>();
        
            String name = roles.getRoleType().toUpperCase();
            //Make sure that all roles start with "ROLE_"
            if (!name.startsWith("ROLE_"))
                name = "ROLE_" + name;
            authority.add(new SimpleGrantedAuthority(name));
        
        return authority;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
