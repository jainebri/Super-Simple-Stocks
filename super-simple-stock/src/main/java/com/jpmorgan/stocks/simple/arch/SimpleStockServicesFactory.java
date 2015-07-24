package com.jpmorgan.stocks.simple.arch;

import com.jpmorgan.stocks.simple.arch.impl.SimpleStockServicesFactoryImpl;
import com.jpmorgan.stocks.simple.services.SimpleStockService;

/**
 * Factory of the services in the Super Simple Stock application. 
 * 
 * As design decision, all possible external systems or high level layers in the application 
 * will access to the services through this factory.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface SimpleStockServicesFactory {
	
	/**
	 * Singleton instance of the factory SimpleStockServicesFactory.
	 */
	public SimpleStockServicesFactory INSTANCE = SimpleStockServicesFactoryImpl.getInstance();
	
	/**
	 * Gets the singleton instance of the Super Simple Service, which implements the five operations
	 * to calculate the dividend yield, P/E Ratio, Stock Price, GBCE All Share Index and record trades 
	 * for a given stock.
	 * 
	 * @return An object of type SimpleStockService, representing a instance of the Super Simple Service
	 */
	public SimpleStockService getSimpleStockService();

}
