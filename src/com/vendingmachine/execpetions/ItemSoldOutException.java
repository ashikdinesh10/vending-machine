package com.vendingmachine.execpetions;

/**
 * This Exception is thrown when a customer requests a product that was sold out
 * 
 * @author Ashik
 *
 */
public class ItemSoldOutException extends RuntimeException{
	
	private String message;

	public ItemSoldOutException(String message) {
		super();
		this.message = message;
	}
	
	@Override
		public String getMessage() {
			return message;
		}

}
