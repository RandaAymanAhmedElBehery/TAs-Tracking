package controllers;

import Exceptions.LoginErrorException;
import Exceptions.UserNameExistsException;
import dao.UserProfileDAO;

public class LoginController {
	
	
	UserProfileDAO userDao;
	public LoginController() {
		userDao = new UserProfileDAO();
	}

	public boolean login(String username, String password) throws LoginErrorException{
		boolean valid_login = userDao.login(username, password);
		
		if(valid_login) {
			System.out.println(username + " Logged inSuccessfully");
			return true;
		}
		else
			throw new LoginErrorException("Incorrect Credentials");
	}

	public boolean sign_up(String username, String password) throws UserNameExistsException{
		boolean signed_up = userDao.sign_up(username, password);
		
		if(signed_up) {
			System.out.println(username + " Signed up Successfully");
			return true;
		}
		else
			throw new UserNameExistsException("Incorrect Credentials");
		
	}

}
