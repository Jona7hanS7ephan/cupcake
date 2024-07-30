package br.com.cupacke.exception;


public class CupackeException extends Exception {

	
	private static final long serialVersionUID = 1292345736039515841L;
	

	/**
	 * @param message
	 */
	public CupackeException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CupackeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
