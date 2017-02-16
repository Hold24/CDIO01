package controller;

import java.util.ArrayList;
import boundary.TUI;
import controller.IUserDAO.DALException;
import controller.UserManagement;
import entity.UserDTO;

public class Main {

	public static void main(String[] args) {
		
		UserManagement um = new UserManagement();
		
		switch (TUI.Menu()) {
		// create user
		case 1:  
			UserDTO user = new UserDTO();
			user.setUserName(TUI.userName());
			user.setCpr(TUI.userCPR());
			user.addRole(TUI.userRole());
			user.setIni(TUI.userInitials());
			try {
				um.createUser(user);
			} catch (DALException e) {
				e.printStackTrace();
			}
			break;
		// Show user list
		case 2: 
			try {
				ArrayList<UserDTO> userList = new ArrayList<UserDTO>(um.getUserList());
				for (int i = 0; i < userList.size(); i++) {
					TUI.showUser(userList.get(i).toString());
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			// Update user
		case 3:
			try {
				um.updateUser(um.getUser(TUI.updateUser()));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			
			break;
			
		}
		
	}

}
