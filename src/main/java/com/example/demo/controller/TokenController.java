package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filters.JwtService;

@RestController
@RequestMapping(ConstantesController.VERSION_API)
@CrossOrigin(origins = "http://localhost:3000")
public class TokenController {
	
	@Autowired JwtService jwtService;
	
	@PostMapping("/token")
	@PreAuthorize("Authenticated")
	public ResponseEntity<String> responseToken(@AuthenticationPrincipal User activeUser){
		return new ResponseEntity<String>(jwtService.createToken(activeUser.getUsername(),new ArrayList<>()), HttpStatus.ACCEPTED);
	}
}
