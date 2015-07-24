package com.jpmorgan.stocks.simple.services;

import com.jpmorgan.stocks.simple.model.Trade;

/**
 * Super Simple Service, which implements the five operations to calculate the dividend yield, 
 * P/E Ratio, Stock Price, GBCE All Share Index and record trades for a given stock.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface SimpleStockService {
	
	/**
	 * Calculates the dividend yield for a given stock.
	 * 
	 * @param stockSymbol Stock symbol.
	 * 
	 * @return A double value which represents the dividend yield for a given stock.
	 * 
	 * @throws Exception A exception raised during the execution of the method due to an error.
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception;
	
	/**
	 * Calculates the P/E Ratio for a given stock.
	 * 
	 * @param stockSymbol Stock symbol.
	 * 
	 * @return A double value which represents the P/E Ratio for a given stock.
	 * 
	 * @throws Exception A exception raised during the execution of the method due to an error.
	 */
	public double calculatePERatio(String stockSymbol) throws Exception;
	
	/**
	 * Record a trade in the Super Simple Stocks application.
	 * 
	 * @param trade Trade object to record.
	 * 
	 * @return True, when the record is successful. Other case, False.
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
	//public int getTradesNumber() throws Exception;
}
