package pl.polsl.forum;

/**
 * Exception for errors while user is logging in.
 * @author Michal Rakoczy
 *
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException(){
		super();
	}
	
	public LoginException(String message) {
		super(message);
	}
}
