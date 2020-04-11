package br.com.reward.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.security.JWTUtil;
import br.com.reward.security.MyUserDetails;
import br.com.reward.util.HTTPUtil;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*") 
public class AuthController {

    @Autowired
	private JWTUtil jwtUtil;

    @Autowired
    private HTTPUtil httpUtil;

    @Autowired
    private UserDetailsService userService;
    
	@RequestMapping(value = "/auth/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String requestToken = httpUtil.getRequestToken(request);
        final String login = jwtUtil.getLogin(requestToken);
        MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(login);
		final String refreshToken = jwtUtil.generateToken(user);
		response.addHeader("Authorization", "Bearer " + refreshToken);
		return ResponseEntity.noContent().build();
	}
}