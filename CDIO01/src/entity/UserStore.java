package entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import entity.IUserDAO.DALException;

public class UserStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2598715957098613225L;
	private ArrayList<UserDTO> userList;

	public UserStore() {
		userList = new ArrayList<UserDTO>();
	}


	public void loadUsersDB(){

	}
	public void saveUsersDB(){

	}

	public List<UserDTO> getUserList() throws DALException {
		return this.userList;
	}

	public void addUser(UserDTO user){
		userList.add(user);
	}

	public void removeUser(UserDTO user){
		userList.remove(user);
	}
	
	public UserDTO getUser(int index) throws DALException {
		return this.getUserList().get(index);
	}

}
