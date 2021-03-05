package com.example.demo.controller.exceptions;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 2140990040978108389L;
	
	private static final String DESCRIPTION = "Bad Request Exception (400)";
	
	public BadRequestException(String detail) {
		super(DESCRIPTION+". "+detail);
	}

}
