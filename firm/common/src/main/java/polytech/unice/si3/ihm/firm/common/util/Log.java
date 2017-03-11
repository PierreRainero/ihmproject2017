package polytech.unice.si3.ihm.firm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Logger class
 *
 */
public class Log {
	/**
	 * Log file of the application
	 */
    private static Logger logger;
    
	/**
	 * Private constructor to hide the public one
	 */
	private Log(){
	}
	
    /**
     * Add a info message to the log
     * @param currentClass calling class
     * @param msg message to add
     */
	public static void info(Class<?> currentClass, String msg){
		setLogClass(currentClass);
		logger.info(msg);
	}
	
    /**
     * Add a debug message to the log
     * @param currentClass calling class
     * @param msg message to add
     * @param obj object linked to the message
     */
	public static void debug(Class<?> currentClass, String msg, Object obj){
		setLogClass(currentClass);
		logger.debug(msg, obj);
	}
	
    /**
     * Add a debug message to the log
     * @param currentClass calling class
     * @param msg message to add
     */
	public static void debug(Class<?> currentClass, String msg){
		setLogClass(currentClass);
		logger.debug(msg);
	}
	
    /**
     * Add a error message to the log
     * @param currentClass calling class
     * @param msg message to add
     * @param obj object linked to the message
     */
	public static void error(Class<?> currentClass, String msg, Object obj){
		setLogClass(currentClass);
		logger.error(msg, obj);
	}
	
    /**
     * Private setter for the log file class
     * @return the log file
     */
	private static void setLogClass(Class<?> currentClass) {
		Log.logger = LoggerFactory.getLogger(currentClass);
	}
}
