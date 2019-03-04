package com.mindtree.mystayapp.exception;

import org.springframework.http.HttpStatus;

public class SignUpException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String requestURI;
	private String message;
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	@Override
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String getRequestURI() {
		return requestURI;
	}
	@Override
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public SignUpException() {
		super();
	}
	public SignUpException(int errorCode, String requestURI, String message) {
		super();
		this.errorCode = errorCode;
		this.requestURI = requestURI;
		this.message = message;
	}
	/**
	 * @param string2 
	 * @param string 
	 * @param internalServerError 
	 * 
	 */
	public SignUpException(HttpStatus internalServerError, String string1, String string2) {
		super(string1);
	}

	/**
	 * @param message
	 */
	public SignUpException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public SignUpException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SignUpException(String message, Throwable cause) {
		super(message, cause);
	
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SignUpException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super();
	
	}
}
