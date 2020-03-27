package br.com.reward.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {

	@Value("${reward.jwt.secret}")
	private String secret;

	@Value("${reward.jwt.expiration}")
	private Long expiration;

    public JWTUtil() {}
    
	public String generateToken(MyUserDetails userDetails) {
        return JWT.create()
                    .withClaim("clientId",userDetails.getClientId())
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(
                        Date.from(
                            LocalDateTime.now()
                                .plusSeconds(expiration)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                    .sign(Algorithm.HMAC512(secret));
    }
    
    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
						.build()
						.verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
						.getSubject();
    }
}