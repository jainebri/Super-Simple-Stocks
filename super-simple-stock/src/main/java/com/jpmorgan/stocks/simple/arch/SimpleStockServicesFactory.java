package com.jpmorgan.stocks.simple.arch;

import com.jpmorgan.stocks.simple.arch.impl.SimpleStockServicesFactoryImpl;
import com.jpmorgan.stocks.simple.services.SimpleStockService;

/**
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface SimpleStockServicesFactory {
	
	/**
	 * 
	 */
	public SimpleStockServicesFactory INSTANCE = SimpleStockServicesFactoryImpl.getInstance();
	
	/**
	 * 
	 * @return
	 */
	public SimpleStockService getSimpleStockService();

}
