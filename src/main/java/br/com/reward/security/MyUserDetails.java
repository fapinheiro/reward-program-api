package br.com.reward.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.reward.enums.RolesEnum;


public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 9169798072600715752L;

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public MyUserDetails(Integer id, String username, String password, List<RolesEnum> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
            .collect(Collectors.toList());;
    }
    
   

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public Integer getId() {
        return this.id;
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

    public boolean hasRole(RolesEnum role) {
        return roles.contains( new SimpleGrantedAuthority(role.getDescricao()) );
    }
}