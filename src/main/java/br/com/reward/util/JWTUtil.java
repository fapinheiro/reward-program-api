package br.com.reward.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.reward.security.MyUserDetails;


@Component
public class JWTUtil {

	@Value("${reward.jwt.secret}")
	private String secret;

	@Value("${reward.jwt.expiration}")
	private Long expiration;

    public JWTUtil() {}
    
	public String generateToken(MyUserDetails userDetails) {
        return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withClaim("id", userDetails.getId())
                    .withArrayClaim("roles", 
                            userDetails.getAuthorities()
                                .stream()
                                .map( role -> role.getAuthority())
                                .collect(Collectors.toList())
                                .stream()
                                .toArray(String[]::new))
                    .withExpiresAt(
                        Date.from(
                            LocalDateTime.now()
                                .plusSeconds(expiration)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                    .sign(Algorithm.HMAC512(secret));
    }
    
    public DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
						.build()
						.verify(token);
    }

    public String getTokenLogin(String token) {
        return validateToken(token).getSubject();
    }

    public Integer getTokenId(String token) {
		return validateToken(token).getClaim("id").asInt();
	}

}