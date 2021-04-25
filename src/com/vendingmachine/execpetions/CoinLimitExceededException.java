package com.vendingmachine.execpetions;

public class CoinLimitExceededException extends RuntimeException {

	private String message;

	public CoinLimitExceededException(String message) {
		super();
		this.message = message;
	}
	
	@Override
		public String getMessage() {
			return message;
	}
}
