package com.vendingmachine.execpetions;

/**
 * This exception is thrown when the customer tries to collect an item without paying full amount 
 * 
 * @author Ashik
 *
 */
public class FullAmountNotPaidException extends RuntimeException {
	
	private String message;
	
	private long remaining;

	public FullAmountNotPaidException(String message, long remaining) {
		super();
		this.message = message;
		this.remaining = remaining;
	}
	
	public long getRemaining() {
		return remaining;
	}
	
	@Override
	public String getMessage() {
		return message + remaining;
	}

}
