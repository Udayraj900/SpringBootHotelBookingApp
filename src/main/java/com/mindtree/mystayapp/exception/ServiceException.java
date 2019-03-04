/**
 * 
 */
package com.mindtree.mystayapp.exception;

/**
 * @author RShaw
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	public ServiceException() {
	
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
