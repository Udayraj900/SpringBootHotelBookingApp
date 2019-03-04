/**
 * 
 */
package com.mindtree.mystayapp.exception;

/**
 * @author RShaw
 *
 */
public class DAOException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	
	public DAOException() {
	}

	
	public DAOException(String arg0) {
		super(arg0);
	}

	
	public DAOException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DAOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
