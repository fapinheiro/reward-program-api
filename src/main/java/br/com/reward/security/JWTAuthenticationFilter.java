package br.com.reward.security;

import static br.com.reward.security.SecurityConstants.EXPIRATION_TIME;
import static br.com.reward.security.SecurityConstants.SECRET;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import br.com.reward.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
            String token = JWT.create()
                    .withClaim("clientId",userDetails.getClientId())
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(Date.from(
                            LocalDateTime.now().plusHours(EXPIRATION_TIME).atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
    
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
