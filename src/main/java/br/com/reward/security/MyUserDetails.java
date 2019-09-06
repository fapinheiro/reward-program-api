package br.com.reward.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 9169798072600715752L;

    private Integer clientId;
    private String username;
    private String password;
 
    public MyUserDetails(Integer clientId, String username, String password) {
        this.clientId = clientId;
        this.username = username;
        this.password = password;
    }
    
    public MyUserDetails(String username, String password) {
        this(null, username, password);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public Integer getClientId() {
        return clientId;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
        return new ArrayList<>();
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