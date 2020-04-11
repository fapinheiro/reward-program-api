package br.com.reward.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.reward.util.HTTPUtil;

import com.auth0.jwt.exceptions.TokenExpiredException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	private JWTUtil jwtUtil;
	
	private HTTPUtil httpUtil;

	private UserDetailsService userService;

	public JWTAuthorizationFilter(AuthenticationManager authManager, JWTUtil jwtUtil, HTTPUtil httpUtil, UserDetailsService userService) {
		super(authManager);
		this.jwtUtil = jwtUtil;
		this.httpUtil = httpUtil;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (httpUtil.hasRequestToken(req)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws TokenExpiredException {
		String token = httpUtil.getRequestToken(request);
		if (token != null) {
			try {
				String login = jwtUtil.getLogin(token);
				MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(login);
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				}
			} catch (TokenExpiredException e) {
				LOG.error("Token expired", e);
				request.setAttribute("expired", e.getMessage());
			} catch (UsernameNotFoundException e) {
				LOG.error("User not found", e);
			}
			return null;
		}
		return null;
	}
}
