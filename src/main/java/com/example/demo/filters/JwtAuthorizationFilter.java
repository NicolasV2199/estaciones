package com.example.demo.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
private final String AUTHORIZATION = "Authorization";
	
	@Autowired
	private JwtService jwtS;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		
		String authHeader = request.getHeader(AUTHORIZATION);
		
		if(jwtS.isBearer(authHeader)) {
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtS.user(authHeader),null,new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
}
