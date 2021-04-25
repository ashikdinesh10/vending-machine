package com.vendingmachine.entities;

/**
 * Items supoorted by vending machine
 * 
 * @author Ashik
 *
 */

public enum Item {
	SPRITE("Sprite", 25), PEPSI("Pepsi", 35), SLICE("slice", 45);
	
	private String name;
	private int price;
	
	private Item(String name, int price) { 
		this.name = name; this.price = price; 
	} 
	
	public String getName(){ 
		return name; 
	}
	
	public long getPrice(){ return price; 
	}

	
}
