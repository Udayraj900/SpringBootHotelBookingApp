/**
 * 
 */
package com.mindtree.mystayapp.exception;

/**
 * @author RShaw
 *
 */
public class FetchException extends DAOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FetchException() {
		
	}

	/**
	 * @param message
	 */
	public FetchException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public FetchException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FetchException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FetchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
