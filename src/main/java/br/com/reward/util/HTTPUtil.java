package br.com.reward.util;

import static br.com.reward.security.SecurityConstants.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.reward.enums.RolesEnum;
import br.com.reward.security.MyUserDetails;

@Component
public class HTTPUtil {

    @Autowired
    private HttpServletRequest request;
    
    public HTTPUtil() {}

    public boolean hasRequestToken() {
        String header = request.getHeader(HEADER_STRING);
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return true;
        }
        return false;
    }

    public String getRequestToken() {
        String header = request.getHeader(HEADER_STRING);
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
        
    public boolean hasRequestRole(RolesEnum role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        if (userDetails != null) {
            return userDetails.hasRole(role);
        }
        return false;
    }

    public MyUserDetails getRequestAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyUserDetails) authentication.getPrincipal();
    }
}