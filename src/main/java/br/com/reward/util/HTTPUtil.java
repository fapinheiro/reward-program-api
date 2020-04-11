package br.com.reward.util;

import static br.com.reward.security.SecurityConstants.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class HTTPUtil {

    public HTTPUtil() {}

    public boolean hasRequestToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return true;
        }
        return false;
    }

    public String getRequestToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
        
}