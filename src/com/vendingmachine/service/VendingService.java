package com.vendingmachine.service;

import java.util.List;

import com.vendingmachine.entities.Coin;
import com.vendingmachine.entities.Item;
import com.vendingmachine.utilities.GenericItems;

/**
 * 
 * This interface defines public APIs for vending machine
 * 
 * @author Ashik
 *
 */
public interface VendingService {
	
	/**
	 * supplier can add item to the inventory
	 * @param item
	 */
	public void addInventoryItem(Item item);
	
	/**
	 * supplier can remove coin from inventory
	 * @param coin
	 */
	
	public void addCoin(Coin coin);
	/**
	 * This Api selects item from the machine and requests the amount for the item selected
	 * @param item
	 * @return
	 */
	
	public long selectItemAndGetPrice(Item item);
	
	/**
	 * Api enables customer to insert coin to the vending machine
	 * @param coin
	 */
	public void insertCoin(Coin coin);
	
	/**
	 * This Api returns the amount in coins if the item is not available
	 * 
	 * @return
	 */
	public List<Coin> refund();
	
	/**
	 * this API returns the items and the change if any
	 * @return
	 */
	public GenericItems<Item, List<Coin>> collectItemAndChange();
	
	/**
	 * this API resets the whole system
	 */
	public void reset();

}
