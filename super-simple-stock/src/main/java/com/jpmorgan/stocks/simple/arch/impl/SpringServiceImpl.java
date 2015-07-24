package com.jpmorgan.stocks.simple.arch.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpmorgan.stocks.simple.arch.SpringService;

/**
 * Implementation of the Spring Service.
 * 
 * @author Jaidermes Nebrijo Duarte
 *
 */
public class SpringServiceImpl implements SpringService {
	
	/**
	 * Logger service for the class.
	 */
	private Logger logger = Logger.getLogger(SpringServiceImpl.class);

	/**
	 * Spring context files pattern defined for the Super Simple Stocks application.  
	 */
	private static final String SPRING_CONTEXT_FILE_NAME = "classpath*:*stocks-*-context.xml";

	/**
	 * Spring context object.
	 */
	private AbstractApplicationContext springContext = null;

	/**
	 * Constructor of the class. The main purpose of the constructor is to load the Spring context for the Super Simple Stocks applicarion. 
	 */
	private SpringServiceImpl(){
		logger.info("Loading Spring Context for Super Simple Stocks.");
		springContext = new ClassPathXmlApplicationContext(SPRING_CONTEXT_FILE_NAME);
		springContext.registerShutdownHook();
		logger.info("Spring Context created !!!! Successfully !!!!");
	}
	
	
	
	/**
	 * Holder class for the singleton factory instance. {@link SpringServiceHolder} is 
	 * loaded on the first execution of {@link SpringServiceImpl#getInstance()} or the first 
	 * access to {@link SpringServiceHolder#INSTANCE}, not before.
     * 
	 * @author Jaidermes Nebrijo Duarte
	 */
	private static class SpringServiceHolder{
		private static final SpringService INSTANCE = new SpringServiceImpl();
	}


	
	/**
	 * Gets the singleton instance of the spring services. 
	 * 
	 * @return An object of the SpringService, which represents the service to access to all 
	 * beans in the spring container. 
	 */
	public static SpringService getInstance(){
		return SpringServiceHolder.INSTANCE;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.arch.SpringService#getBean(java.lang.String, java.lang.Class)
	 */
	public <T> T getBean(String beanName, Class<T> objectClass) {
		return springContext.getBean(beanName, objectClass);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.arch.SpringService#getBean(java.lang.Class)
	 */
	public <T> T getBean(Class<T> objectClass) {
		return springContext.getBean(objectClass);
	}

}
