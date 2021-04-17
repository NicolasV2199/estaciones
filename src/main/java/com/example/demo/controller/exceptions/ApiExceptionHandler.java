package com.example.demo.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		BadRequestException.class,
		org.springframework.web.HttpRequestMethodNotSupportedException.class,
		org.springframework.web.bind.MethodArgumentNotValidException.class,
		org.springframework.security.core.userdetails.UsernameNotFoundException.class
	})
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
		UnauthorizedException.class,
		org.springframework.security.access.AccessDeniedException.class,
		com.auth0.jwt.exceptions.JWTVerificationException.class
	})
	public void unauthorized(HttpServletRequest request, Exception exception) {}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ErrorMessage notFound(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorMessage error(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

}
