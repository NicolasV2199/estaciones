package com.example.demo.controller.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1834539324389686914L;
	
	private static final String DESCRIPTION = "Bad Request Exception (400)";
	
	public NotFoundException(String detail) {
		super(DESCRIPTION+". "+detail);
	}
}
