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
		else if (x == 3){
			try {
				new Main().startDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	private void startDB () throws Exception {
		UserManagement um = new UserManagement();

		//		try {
		//			um.loadUsersDB();
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		boolean on = true;
		do{
			switch (TUI.Menu()) {
			// create user
			case 1: 
				createUserDB(um);
				break;
				// Show user list
			case 2: 
				showUserTable(um);
				TUI.newLine();
				break;
				// Update user
			case 3:
				updateUserDB(um);
				break;
			case 4:
				deleteUserDB(um);
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

	private void deleteUserDB(UserManagement um) throws Exception {
		try {
			int id;
			do{
				id = TUI.chooseUser();
				if(!um.checkForUser(id))
					TUI.nullUser();
			} while(!um.checkForUser(id));
			um.deleteUserDB(id);
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

	private void createUserDB(UserManagement um) throws Exception {
		UserDTO user = new UserDTO();
		int x;
		do{
			x = TUI.userId();
			if(um.checkForUser(x))
				TUI.idTaken();
		} while(um.checkForUser(x));
		user.setUserId(x);
		user.setUserName(TUI.userName());
		user.setIni(TUI.userInitials());
		user.setPassword(um.generatePassword());
		user.setCpr(TUI.userCPR());
		user.setRoles(TUI.userRole());
		um.writeUserDB(user);


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
	
	private void showUserTable(UserManagement um) throws Exception {
		um.showUsersDB();
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
				TUI.showUser(user.toString());
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
	private void updateUserDB(UserManagement um) throws Exception {


		try {
			
			UserDTO user = new UserDTO();
			int id;
			do{
				id = TUI.chooseUser();
				if(!um.checkForUser(id))
					TUI.nullUser();
			} while(!um.checkForUser(id));
			
			user = um.loadUserDB(id);
			boolean updateUser = true;
			do {
				System.out.println(user.toString());
				switch(TUI.updateMenu()) {
				case 1:
					String name = TUI.userName();
					um.updateDBUser(name, "name", id);
					user.setUserName(name);
					break;
				case 2:
					String CPR = TUI.userCPR();
					um.updateDBUser(CPR, "cpr", id);
					user.setCpr(CPR);
					break;
				case 3: 
					
					int x = TUI.changeUserRole();
					if (x == 1) {
						user.roleRemover(TUI.removeRole(user.getRoles()));
					}
					else if (x == 2) {
						if (user.getRoles().stream().count() == 4){
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
						um.updateDBUser(user.getRoles().toString(), "roles", user.getUserId());
					}
					break;
				case 4: 
					String ini = TUI.userInitials();
					um.updateDBUser(ini, "initials", id);
					user.setIni(ini);
					break;
				case 5:
					String pw = TUI.userPassword();
					um.updateDBUser(pw, "password", id);
					user.setPassword(pw);
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