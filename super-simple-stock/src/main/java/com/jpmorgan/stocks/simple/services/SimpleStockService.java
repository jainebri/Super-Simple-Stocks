package com.jpmorgan.stocks.simple.services;

import com.jpmorgan.stocks.simple.model.Trade;

/**
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface SimpleStockService {
	
	/**
	 * 
	 * @param stockSymbol
	 * @return
	 * @throws Exception
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception;
	
	/**
	 * 
	 * @param stockSymbol
	 * @return
	 * @throws Exception
	 */
	public double calculatePERatio(String stockSymbol) throws Exception;
	
	/**
	 * 
	 * @param trade
	 * @return
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * 
	 * @param stockSymbol
	 * @return
	 * @throws Exception
	 */
	public double calculateStockPrice(String stockSymbol) throws Exception;
	
	/**
	 * Calculates the GBCE All Share Index using the geometric mean of prices for all stocks.
	 * For the technical test purpose we have assumed the next:
	 * 
	 * 1. Because the geometric mean is undefined when some price is zero, we will consider only 
	 *    the stock's prices greater than zero.
	 * 2. If all prices are zero then the value for the GBCE All Share Index is zero.
	 * 
	 * @return
	 * @throws Exception
	 */
	public double calculateGBCEAllShareIndex() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getTradesNumber() throws Exception;
}
