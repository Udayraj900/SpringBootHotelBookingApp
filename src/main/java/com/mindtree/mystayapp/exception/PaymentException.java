package com.mindtree.mystayapp.exception;

public class PaymentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5169923527591478731L;
	private int errorCode;
	private String requestURI;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PaymentException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public PaymentException() {
	}

	public PaymentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
	}

}
