package com.jpmorgan.stocks.simple.backend.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jpmorgan.stocks.simple.backend.StocksEntityManager;
import com.jpmorgan.stocks.simple.model.Stock;
import com.jpmorgan.stocks.simple.model.Trade;

/**
 * Implementation of the Stock Entity Manager.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public class StocksEntityManagerImpl implements StocksEntityManager {

	/**
	 *
	 */
	private Logger logger = Logger.getLogger(StocksEntityManagerImpl.class);

	/**
	 * 
	 */
	private HashMap<String, Stock> stocks = null;

	/**
	 * 
	 */
	private ArrayList<Trade> trades = null;

	/**
	 * 
	 */
	public StocksEntityManagerImpl(){
		trades = new ArrayList<Trade>();
		stocks = new HashMap<String, Stock>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getStocks()
	 */
	public HashMap<String, Stock> getStocks() {
		return stocks;
	}

	/**
	 * 
	 * @param stocks
	 */
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getTrades()
	 */
	public ArrayList<Trade> getTrades() {
		return trades;
	}

	/**
	 * 
	 * @param trades
	 */
	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#recordTrade(com.jpmorgan.stocks.simple.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception{
		boolean result = false;
		try{
			result = trades.add(trade);
		}catch(Exception exception){
			throw new Exception("An error has occurred recording a trade in the system's backend.", exception);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getTradesNumber()
	 */
	public int getTradesNumber() {
		return trades.size();
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getStockBySymbol(java.lang.String)
	 */
	public Stock getStockBySymbol(String stockSymbol){
		Stock stock = null;
		try{
			if(stockSymbol!=null){
				stock = stocks.get(stockSymbol);
			}
		}catch(Exception exception){
			logger.error("An error has occurred recovering the stock object for the stock symbol: "+stockSymbol+".", exception);
		}
		return stock;
	}



}
