package com.jpmorgan.stocks.simple.arch.utils.impl;

import java.util.Calendar;
import java.util.Date;

import com.jpmorgan.stocks.simple.arch.utils.DatesUtils;

/**
 * Class containing functionality useful to work with dates.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public class DatesUtilsImpl implements DatesUtils {

	/**
	 * Constructor of the class.
	 */
	public DatesUtilsImpl(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.arch.utils.DatesUtils#getNowMovedMinutes(int)
	 */
	public Date getNowMovedMinutes(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}
}
