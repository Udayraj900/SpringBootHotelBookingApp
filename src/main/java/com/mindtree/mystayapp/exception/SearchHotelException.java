package com.mindtree.mystayapp.exception;

public class SearchHotelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	/**
	 * @param errorCode
	 * @param requestURI
	 * @param message
	 */
	public SearchHotelException(int errorCode, String requestURI, String message) {
		this.errorCode = errorCode;
		this.requestURI = requestURI;
		this.message = message;
	}
	public SearchHotelException(int errorCode,  String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	/**
	 * 
	 */
	public SearchHotelException() {
		super();
	}
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SearchHotelException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public SearchHotelException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param message
	 */
	public SearchHotelException(String message) {
		super(message);
	}
	/**
	 * @param cause
	 */
	public SearchHotelException(Throwable cause) {
		super(cause);
	}
	
	
}
