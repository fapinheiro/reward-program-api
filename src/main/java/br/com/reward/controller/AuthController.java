package br.com.reward.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.util.JWTUtil;
import br.com.reward.dto.RefreshTokenDTO;
import br.com.reward.exception.AuthorizationException;
import br.com.reward.security.MyUserDetails;
import br.com.reward.security.SecurityConstants;
import br.com.reward.util.HTTPUtil;

@RestController
@RequestMapping(SecurityConstants.API_URL)
@CrossOrigin
public class AuthController {

    @Autowired
	private JWTUtil jwtUtil;

    @Autowired
    private HTTPUtil httpUtil;
    
	@RequestMapping(value = "/auth/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<RefreshTokenDTO> refreshToken(HttpServletResponse response) {

        final String requestToken = httpUtil.getRequestToken();
        final String login = jwtUtil.getTokenLogin(requestToken);
        MyUserDetails user = httpUtil.getRequestAuthenticatedUser();
        if (!user.getUsername().equals(login)) {
            throw new AuthorizationException("Invalid refresh token");
        }

        final String refreshToken = jwtUtil.generateToken(user);
        response.addHeader("Authorization", "Bearer " + refreshToken);
     
        RefreshTokenDTO dto = new RefreshTokenDTO();
        dto.setToken("Bearer " + refreshToken);

        return ResponseEntity.ok().body(dto);
	}
}