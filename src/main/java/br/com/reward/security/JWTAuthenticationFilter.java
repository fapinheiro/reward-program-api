package br.com.reward.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.reward.entity.User;
import br.com.reward.util.JWTUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getLogin(), creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        final String expiredMsg = (String) req.getAttribute("expired");
        if (expiredMsg != null) {
            
            final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
        
        } else {

            MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
    
            // res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    
            PrintWriter out = res.getWriter();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            out.print(String.format("{ \"token\" : \"Bearer %s\"}", token));

            // out.flush();
            // res.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,
            // Content-Range, Content-Disposition, Content-Description");
        }

    }

    // @Bean
    // public JWTAuthenticationFilter getJWTAuthenticationFilter() {
    // final JWTAuthenticationFilter filter = new
    // JWTAuthenticationFilter(authenticationManager());
    // filter.setFilterProcessesUrl("/api/auth/login");
    // return filter;
    // }
}
