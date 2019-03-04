/**
 * 
 */
package com.mindtree.mystayapp.exception;

/**
 * @author RShaw
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

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
	
	
	public ApplicationException() {
		super();
	}
	public ApplicationException(int errorCode, String requestURI, String message) {
		this.errorCode = errorCode;
		this.requestURI = requestURI;
		this.message = message;
	}
	
	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApplicationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
	
	}

}
