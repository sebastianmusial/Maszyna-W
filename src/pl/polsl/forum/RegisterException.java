package pl.polsl.forum;

/**
 * Exception for errors while user is logging in.
 * @author Michal Rakoczy
 *
 */
public class RegisterException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegisterException() {
		
	}
	
	public RegisterException(String message) {
		super(message);
	}
}
