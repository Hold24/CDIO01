package entity;
import java.util.ArrayList;
import java.util.List;

import controller.IUserDAO.DALException;

public class UserStore {
	
	private ArrayList<UserDTO> userList;
	
	public UserStore() {
		userList = new ArrayList<UserDTO>();
	}
	
	public List<UserDTO> getUserList() throws DALException {
		return this.userList;
	}
	
	public void addUser(UserDTO user){
		userList.add(user);
	}
	
}
