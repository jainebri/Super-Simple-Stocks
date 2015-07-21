package com.jpmorgan.stocks.simple.arch;

import com.jpmorgan.stocks.simple.arch.impl.SpringServiceImpl;

/**
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public interface SpringService {
	/**
	*
	*/
	public SpringService INSTANCE = SpringServiceImpl.getInstance();	
	
	/**
	* @param beanName
	* @return
	*/
	public <T extends Object> T getBean(String beanName, Class<T> objectClass);

	/**
	* @param objectClass
	* @return
	*/
	public <T extends Object> T getBean(Class<T> objectClass);

}
