package com.jpmorgan.stocks.simple.arch.utils.impl;

import java.util.Calendar;
import java.util.Date;

/**
 * Class containing functionality useful to work with dates.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public class DatesUtilsImpl {

	/**
	 * 
	 */
	public DatesUtilsImpl(){
		
	}
	
	/**
	 * 
	 * @param minutes
	 * @return
	 */
	public Date getNowSpliceMinutes(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}
}
