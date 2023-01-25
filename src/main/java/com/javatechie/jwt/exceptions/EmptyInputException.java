package com.javatechie.jwt.exceptions;

public class EmptyInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCOde;
	private String errorMessage;
	public String getErrorCOde() {
		return errorCOde;
	}
	public void setErrorCOde(String errorCOde) {
		this.errorCOde = errorCOde;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public EmptyInputException(String errorCOde, String errorMessage) {
		this.errorCOde = errorCOde;
		this.errorMessage = errorMessage;
	}
	public EmptyInputException() {
		
	}
	

}
