package com.vendingmachine.entities;

/**
 * Coins supported by vending machine
 * 
 * @author Ashik
 *
 */
public enum Coin {

	ONERUPEE(1), TWORUPEE(2), FIVERUPEE(5), TENRUPEE(10);
	
	private int denomination;
	
	private Coin(int denomination){
        this.denomination = denomination;
    }
	
	public int getDenomination(){
        return denomination;
    }
	
}
