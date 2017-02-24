package entity;

import entity.IUserDAO.DALException;

public class UserNotFoundException extends DALException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
