package com.vendingmachine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vendingmachine.entities.Coin;
import com.vendingmachine.entities.Item;
import com.vendingmachine.execpetions.CoinLimitExceededException;
import com.vendingmachine.execpetions.FullAmountNotPaidException;
import com.vendingmachine.execpetions.ItemSoldOutException;
import com.vendingmachine.execpetions.NoSufficientChangeException;
import com.vendingmachine.inventory.Inventory;
import com.vendingmachine.utilities.GenericItems;

public class VendingServiceImpl implements VendingService {
	
	//The total number of products the vending machine can hold at a time is 120
	private static final int ITEM_LIMIT = 120;
	
	//Total number of coins the vending machine can hold is 500
	private static final int COIN_LIMIT = 500;
	
	//to add coins to the machine
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	
	//to add items to the machine
	private Inventory<Item> itemInventory = new Inventory<Item>();
	
	private long totalSalesAmount;
	
	private Item currentItem;
	
	private long currentBalance;
	
	/**
	 * constructor to initialize values
	 */
	public VendingServiceImpl() {
		initialize();
	}
	
	@Override
	public void addInventoryItem(Item item) {
		while (itemInventory.getSize() <= ITEM_LIMIT) {
			itemInventory.addItem(item);
		}		
	}

	@Override
	public void addCoin(Coin coin) {
		while (cashInventory.getSize() <= COIN_LIMIT) {
			cashInventory.addItem(coin);
		}		
	}
	
	public void removeCoin(Coin coin) {
		if (cashInventory.getSize() > 0) {
			cashInventory.deductItem(coin);
		}
	}
	
	/**
	 * initialize machine with ten coins of each denomination and ten cans of each item
	 */
	private void initialize() {
		
		for(Coin coin : Coin.values()) {
			cashInventory.put(coin, 10);
		}
		
		for(Item item : Item.values()) {
			itemInventory.put(item, 10);
		}
	}

	@Override
	public long selectItemAndGetPrice(Item item) {
		if (itemInventory.hasItem(item)) {
			currentItem = item;
			return currentItem.getPrice();
		}
		else {
		  throw new ItemSoldOutException("Item Sold out, please select anothe one ");
	  }
	}
	

	@Override
	public void insertCoin(Coin coin) {
		currentBalance += coin.getDenomination();
		
		if (cashInventory.getSize() <= COIN_LIMIT)  {
 		cashInventory.addItem(coin);
		} else {
			throw new CoinLimitExceededException("No space for Coins in inventory, please contact supplier");
		}
	}

	@Override
	public GenericItems<Item, List<Coin>> collectItemAndChange() {
		Item item = collectItem();
		totalSalesAmount += currentItem.getPrice();
		
		List<Coin> change = collectChange();
		
		return new GenericItems<Item, List<Coin>>(item, change);
	}

	@Override
	public void reset() {
		cashInventory.clear();
		itemInventory.clear();
		totalSalesAmount = 0;
		currentItem = null;
		currentBalance = 0;
	}
	
	@Override
	public List<Coin> refund() {
		List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		return refund;
	}
	
	private Item collectItem() throws NoSufficientChangeException, FullAmountNotPaidException {
		
		//checks if full amount is paid
		if(isFullPaid()) {
			
			//checks if there is sufficient change available
			if(hasSufficientChange()) {
				itemInventory.deductItem(currentItem);
				return currentItem;
			}
			
			throw new NoSufficientChangeException("No Sufficient change in the inventory");
		}
		
		long remainingBalance = currentItem.getPrice() - currentBalance;
		throw new FullAmountNotPaidException("price not full paid , remaining", remainingBalance);
		
	}
	

	private List<Coin> getChange(long changeAmount) {
		List<Coin> changes = Collections.EMPTY_LIST;
		
		if (changeAmount > 0) {
			changes = new ArrayList<Coin>();
			long balance = changeAmount;
			
			//checks for balance in each denomination
			while (balance > 0) {
				if(balance >= Coin.ONERUPEE.getDenomination() 
							&& cashInventory.hasItem(Coin.ONERUPEE)) {
					changes.add(Coin.ONERUPEE);
					balance = balance - Coin.ONERUPEE.getDenomination();
					continue;
				} else if(balance >= Coin.TWORUPEE.getDenomination()
						 && cashInventory.hasItem(Coin.TWORUPEE)) {
					changes.add(Coin.TWORUPEE);
					balance -= Coin.TWORUPEE.getDenomination();
					continue;
				} else if(balance >= Coin.FIVERUPEE.getDenomination()
						 && cashInventory.hasItem(Coin.FIVERUPEE)) {
					changes.add(Coin.FIVERUPEE);
					balance -= Coin.FIVERUPEE.getDenomination();
					continue;
			   } else if(balance >= Coin.TENRUPEE.getDenomination()
						 && cashInventory.hasItem(Coin.TENRUPEE)) {
					changes.add(Coin.TENRUPEE);
					balance -= Coin.TENRUPEE.getDenomination();
					continue;	
		      } else {
		    	  throw new NoSufficientChangeException("No Sufficient change, please select another product");
		      }
			}
		}
		return changes;
	}

	private boolean hasSufficientChange() {
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long amount) {
		boolean hasChange = true;
		try {
			getChange(amount);
		} catch(NoSufficientChangeException e) {
			hasChange = false;
		}
		return hasChange;
	}

	private boolean isFullPaid() {
		return currentBalance >= currentItem.getPrice();
	}
	
	private List<Coin> collectChange() {
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}

	private void updateCashInventory(List<Coin> change) {
		for(Coin coin : change) {
			cashInventory.deductItem(coin);
		}
		
	}
	
	public void printStatus() {
		System.out.println("Total Sales " + totalSalesAmount);
		System.out.println("Current Item Inventory: " + itemInventory);
		System.out.println("Current Cash Inventory " + cashInventory);
	}


}
