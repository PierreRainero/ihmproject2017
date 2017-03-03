package polytech.unice.si3.ihm.firm.exceptions;

/**
 * 
 * Exception class for the content (json)
 *
 */
public class ContentException extends Exception{
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -6320078439810516761L;

	/**
	 * Normal constructor
	 * @param msg message to display
	 */
	public ContentException(String msg){
		super(msg);
	}

}
