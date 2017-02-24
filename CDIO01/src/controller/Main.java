package controller;

import java.util.ArrayList;
import java.util.List;
import boundary.TUI;
import entity.UserDTO;
import entity.UserManagement;
import entity.IUserDAO.DALException;

public class Main {
	

	public static void main(String[] args) throws DALException {
		int x = TUI.whichProgram();
		if(x == 1)
			new Main().start();
		else if(x == 2)
			new Main().startText();
		else if (x == 3)
			new Main().startDB();
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
	
	private void startText () throws DALException {
		UserManagement um = new UserManagement();

		um.loadUsersText();
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
				um.saveUsersText(um.getUserStore());
				TUI.shutDown();
				break;
			default: 
				break;

			}
		} while(on);	
	}
	
	private void startDB () throws DALException {
		UserManagement um = new UserManagement();

		try {
			um.loadUsersDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		boolean idTaken = true;
		int x;
		do{
			x = TUI.userId();
			try {
				int b = x;
				idTaken = um.getUserList().stream().anyMatch(e -> e.getUserId() == b);
			} catch (DALException e1) {
				e1.printStackTrace();
			}
			if(idTaken)
				TUI.idTaken();
		} while(idTaken);
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
			//TUI.newLine();
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
			UserDTO user = null;
			do {
				id = TUI.chooseUser();
				ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
				userList = (ArrayList<UserDTO>) um.getUserList();
				for(int i = 0; i < userList.size(); i++){
					if(id == userList.get(i).getUserId()){
						user = userList.get(i);
						userExists = true;
						//What to update on user
					}

				}
				if(userExists == false)
					TUI.nullUser();
			} while (!userExists);
			boolean updateUser = true;
			do {
				switch(TUI.updateMenu()) {
				case 1:
					user.setUserName(TUI.userName());
					break;
				case 2:
					//TUI.nextLine();
					user.setCpr(TUI.userCPR());
					break;
				case 3: 
					//TUI.nextLine();
					List<String> roles = new ArrayList<String>();
					roles = user.getRoles();
					int x = TUI.changeUserRole();
					if (x == 1) {
						user.roleRemover(TUI.removeRole(user.getRoles()));
					}
					else if (x == 2) {
						if (roles.stream().count() == 4){
							System.out.println("This user has all avaliable roles.\n");
							break;
						}
						boolean hasRole = true;
						do {
							String role = TUI.addRole();
							if (!user.getRoles().contains(role)){
								user.addRole(role);
								hasRole = false;
							}
							else
								TUI.hasRole();
						} while (hasRole);
					}
					break;
				case 4: 
					//TUI.nextLine();
					user.setIni(TUI.userInitials());
					break;
				case 5:
					user.setPassword(TUI.userPassword());
					break;
				case 6:
					updateUser = false;
					break;
				default:
					break;
				}
			} while (updateUser);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}