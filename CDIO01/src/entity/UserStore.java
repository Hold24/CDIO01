package entity;
import java.util.ArrayList;
import java.util.List;

import controller.IUserDAO.DALException;

public class UserStore {

	private ArrayList<UserDTO> userList;

	public UserStore() {
		userList = new ArrayList<UserDTO>();
		//		UserDTO user1 = new UserDTO();
		//		user1.addRole("Admin");
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

	public int nextId(){
		int id = 11;
		ArrayList<UserDTO> temp = new ArrayList<UserDTO>();	
		if(userList.size() >= 1){
			for(int i = 0; i < userList.size() - 1; i++){
				if(userList.get(i).getUserId() > userList.get(i + 1).getUserId())
					temp.add(i + 1, userList.get(i));
				else
					temp.add(i, userList.get(i));
			}
		}
		for(int i = 0; i < temp.size(); i++){
			if(id == temp.get(i).getUserId())
				id++;
		}
		return id;
	}

}
