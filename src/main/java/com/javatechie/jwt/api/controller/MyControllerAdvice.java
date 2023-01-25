package com.javatechie.jwt.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javatechie.jwt.exceptions.BusinessException;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> handleException(BusinessException e) {
	    Map<String, String> errorResponse = new HashMap<>();

	    errorResponse.put("message", e.getLocalizedMessage());
	    errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
	    return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
}
