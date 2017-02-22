package entity;
import java.util.ArrayList;
import java.util.List;

import entity.IUserDAO.DALException;

public class UserStore {

	private ArrayList<UserDTO> userList;

	public UserStore() {
		userList = new ArrayList<UserDTO>();
//		UserDTO user1 = new UserDTO();
//		user1.addRole("Admin");
//		user1.addRole("Pharmacist");
//		user1.addRole("Foreman");
//		user1.addRole("Operator");
//		user1.setUserName("Dr. Bitch");
//		user1.setCpr("123456-7890");
//		user1.setIni("DB");
//		user1.setUserId(11);
//		userList.add(user1);
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

}
