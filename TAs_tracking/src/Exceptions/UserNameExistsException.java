package Exceptions;

public class UserNameExistsException extends Exception {
	
	public UserNameExistsException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}

}
