package com.vendingmachine.factory;

import com.vendingmachine.service.VendingService;
import com.vendingmachine.service.VendingServiceImpl;

/**
 * 
 * Factory class to initialize different instances of vending machine
 * 
 * this can be extended for different types of vending machine by passing vending machine type
 * 
 * @author Ashik
 *
 */
public class VendingMachineFactory {
	
	public static VendingService createVendingMachine() {
		return new VendingServiceImpl();
	}

}
