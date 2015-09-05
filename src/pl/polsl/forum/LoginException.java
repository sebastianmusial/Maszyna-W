package pl.polsl.forum;

public class LoginException extends Exception {

	public LoginException(){
		super();
	}
	
	public LoginException(String message) {
		super(message);
	}
}
