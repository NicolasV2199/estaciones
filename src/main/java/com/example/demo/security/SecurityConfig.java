package com.example.demo.security;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.filters.JwtAuthorizationFilter;
import com.example.demo.services.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final String LOGIN_URL = "/login";
	public static final String SAVE_USER_URL = "/api/usuario";
	
	
	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.
		 csrf().
		 	disable().
		 httpBasic().
		 and().
		 authorizeRequests().
		 	antMatchers(HttpMethod.POST,LOGIN_URL).
		 		permitAll().
		 	antMatchers(HttpMethod.POST, SAVE_USER_URL).
		 		permitAll().
		 	anyRequest().
		 		authenticated().
		 and().
		 logout().
		 and().
		 cors().
		 and().
		 sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilter(jwtAuthorizationFilter());
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(this.authenticationManager());
	}
	
	/*@Bean // configuración centralizada de cors
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/login", configuration);
		return source;
	}
}
