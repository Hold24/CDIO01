package controller;

import java.util.ArrayList;
import boundary.TUI;
import controller.IUserDAO.DALException;
import controller.UserManagement;
import entity.UserDTO;

public class Main {

	public static void main(String[] args) {
		new Main().start();
	}

	private void start() {
		// TODO Auto-generated method stub
		UserManagement um = new UserManagement();
		boolean on = true;
		do{
			switch (TUI.Menu()) {
			// create user
			case 1: 
				createUser(um);
				break;
				// Show user list
			case 2: 
				showUserlist(um);
				break;
				// Update user
			case 3:
				updateUser(um);
				break;
			case 4:
				deleteUser(um);
				break;
			case 5:
				on = false;
				TUI.shutDown();
				break;
			default: 
				break;

			}
		} while(on);
	}

	private void deleteUser(UserManagement um) {
		try {
			int id;
			boolean userExists = false;
			do{
				id = TUI.chooseUser();
				ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
				userList = (ArrayList<UserDTO>) um.getUserList();
				for(int i = 0; i < userList.size(); i++){
					if(id == userList.get(i).getUserId()){
						um.deleteUser(id);
						userExists = true;
						break;
					}
				}
				if(userExists == false)
					TUI.nullUser();
			} while(!userExists);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createUser(UserManagement um) {
		UserDTO user = new UserDTO();
		boolean userExists;
		do{
			int x = TUI.userId();
			if(um.getUserList().stream().anyMatch())
				userExists = false;
		} while(userExists);
		user.setUserId(x);
		user.setUserName(TUI.userName());
		user.setCpr(TUI.userCPR());
		user.setRoles(TUI.userRole());
		user.setIni(TUI.userInitials());
		try {
			um.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	private void showUserlist(UserManagement um) {
		try {
			ArrayList<UserDTO> userList = new ArrayList<UserDTO>(um.getUserList());
			TUI.newLine();
			for (int i = 0; i < userList.size(); i++) {
				TUI.showUser(userList.get(i).toString());
			}
			TUI.newLine();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateUser(UserManagement um) {
		try {
			int id;
			boolean userExists = false;
			do {
				id = TUI.chooseUser();
				ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
				userList = (ArrayList<UserDTO>) um.getUserList();
				for(int i = 0; i < userList.size(); i++){
					if(id == userList.get(i).getUserId()){
						um.updateUser(um.getUser(id));
						userExists = true;
						break;
					}
				}
				if(userExists == false)
					TUI.nullUser();
			} while (!userExists);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
