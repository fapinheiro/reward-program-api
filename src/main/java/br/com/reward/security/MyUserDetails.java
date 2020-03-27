package br.com.reward.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.reward.enums.RolesEnum;


public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 9169798072600715752L;

    private Integer clientId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public MyUserDetails(Integer clientId, String username, String password, Set<RolesEnum> roles) {
        this(clientId, username, password);
        this.roles = roles.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
            .collect(Collectors.toList());;
    }

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
        return this.clientId;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
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