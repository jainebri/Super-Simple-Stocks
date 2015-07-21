package com.jpmorgan.stocks.simple.backend;

import java.util.ArrayList;
import java.util.HashMap;

import com.jpmorgan.stocks.simple.model.Stock;
import com.jpmorgan.stocks.simple.model.Trade;

/**
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface StocksEntityManager {
	
	/**
	 * 
	 * @param trade
	 * @return
	 * @throws Exception
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Trade> getTrades();
	
	/**
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public Stock getStockBySymbol(String stockSymbol);
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Stock> getStocks();
}
