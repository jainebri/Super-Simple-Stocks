package com.jpmorgan.stocks.simple.arch.utils;

import java.util.Date;

/**
 * Provides methods to work with dates. For the moment the unique method is used for the unit tests.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface DatesUtils {

	/**
	 * Get the current date an time with moved the number of minutes specified by the value of the parameter <i>minutes</i>.
	 * 
	 * @param minutes Number of minutes used to move the current date time. If used negative values, the time is moved back.
	 * 
	 * @return The current time moved by the number of minutes pecified by the value of the parameter <i>minutes</i>.
	 */
	public Date getNowMovedMinutes(int minutes);
}
