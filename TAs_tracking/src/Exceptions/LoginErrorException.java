package Exceptions;

public class LoginErrorException extends Exception {
	
	public LoginErrorException(String message) {
		super(message);
		
	}
	
	public String getMessage() {
		return super.getMessage();
	}

}
