package com.example.demo.controller.exceptions;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2690499812083774647L;
	
	private static final String DESCRIPTION = "Unauthorized Exception (401)";
	
	
	public UnauthorizedException(String detail) {
		super(DESCRIPTION+". "+detail);
	}

}
