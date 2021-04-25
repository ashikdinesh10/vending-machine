package com.vendingmachine.execpetions;

/**
 * This exception is thrown when the machine does not have sufficient change to complete the request
 * 
 * @author Ashik
 *
 */
public class NoSufficientChangeException extends RuntimeException {
	
	private String message;

	public NoSufficientChangeException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
