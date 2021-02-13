package com.example.demo.filters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {
	
private final String BEARER = "Bearer ";
	
	private final String ISSUER = "soft_jhon";
	private final String USER = "user";
	private final String ROLES = "roles";
	private final String SECRET_KEY = "secret_key"; 
	private final int EXPIRES_IN_MILLISECOND = 3600000;
	
	public String createToken(String user, List<String> roles) {
		
		String v =  JWT.create()
				.withIssuer(ISSUER)
				.withIssuedAt(new Date())
				.withNotBefore(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRES_IN_MILLISECOND))
				.withClaim(USER, user)
				.withArrayClaim(ROLES, roles.toArray(new String[0]))
				.sign(Algorithm.HMAC256(SECRET_KEY));
		return v;
	}
	
	public boolean isBearer(String authorization) {
		return authorization!=null && authorization.startsWith(BEARER) && authorization.split("\\.").length==3;
	}
	
	public String user(String authorization) {
		return this.verify(authorization).getClaim(USER).asString();
	}

	private DecodedJWT verify(String authorization) throws JWTVerificationException {
		if(!this.isBearer(authorization)) {
			throw new JWTVerificationException("ups! invalid token, without BERRER");
		}
		try {
			return JWT.require(Algorithm.HMAC256(SECRET_KEY))
					.withIssuer(ISSUER).build()
					.verify(authorization.substring(BEARER.length()));
			
		}catch(Exception e) {
			throw new JWTVerificationException("ups! invalid token");
		}
	}
	
	public List<String> roles(String authorization){
		return  new ArrayList<>();
	}
}
