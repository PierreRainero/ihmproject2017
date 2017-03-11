package polytech.unice.si3.ihm.firm.common.util;

/**
 * 
 * Class to apply regex
 *
 */
public class Regex {

	
	/**
	 * Private constructor to hide the public one
	 */
	private Regex(){
		
	}
	
	public static boolean isPostalCode(String s){
		if(!isInteger(s))
			return false;
		
		if(s.length()!=5)
			return false;
		
		return true;
	}
	
	public static boolean isInteger(String s) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) 
	            	return false;
	            else 
	            	continue;
	        }
	        if(Character.digit(s.charAt(i),10) < 0) 
	        	return false;
	    }
	    return true;
	}
}
