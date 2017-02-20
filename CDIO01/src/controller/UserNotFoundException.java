package controller;

import controller.IUserDAO.DALException;

public class UserNotFoundException extends DALException {

	public UserNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
