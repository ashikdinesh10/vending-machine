package com.vendingmachine.utilities;

/**
 * A parameterized utility class to hold two different objects
 * 
 * @author Ashik
 *
 * @param <E1>
 * @param <E2>
 */
public class GenericItems<E1, E2> {
	
	private E1 firstItem;
	private E2 secondItem;
	
	public GenericItems(E1 firstItem, E2 secondItem) {
		super();
		this.firstItem = firstItem;
		this.secondItem = secondItem;
	}
	
	public E1 getFirst(){
        return firstItem;
    }
	
	public E2 getSecond(){
        return secondItem;
    }
	
}
