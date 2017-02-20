package controller;

import java.util.List;

import boundary.TUI;
import entity.UserDTO;
import entity.UserStore;
import entity.passwordGenerator;

public class UserManagement implements IUserDAO {

	UserStore us = new UserStore();
	//private int id = 11;
	passwordGenerator pg = new passwordGenerator();


	@Override
	public UserDTO getUser(int userId) throws DALException {
		
		for(int i = 0; i < us.getUserList().size(); i++){
			if(us.getUserList().get(i).getUserId() == userId)
				return us.getUserList().get(i);
		}
		throw new UserNotFoundException("userID " + userId + " not found.");
	}
	

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		return us.getUserList();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		user.setPassword(pg.createPassword());
		us.addUser(user);

	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		//Update user
		switch (TUI.updateMenu()) {
		case 1:
			user.setUserName(TUI.userName());
			break;
		case 2:
			TUI.nextLine();
			user.setCpr(TUI.userCPR());
			break;
		case 3: 
			if (TUI.changeUserRole() == 1) {
				user.roleRemover(TUI.removeRole(user.getRoles()));
			}
			else {
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
			TUI.nextLine();
			user.setIni(TUI.userInitials());
			break;
		case 5: 
			user.setPassword(TUI.userPassword());
			break;
		default:
			break;
		}

	}


	@Override
	public void deleteUser(int userId) throws DALException {
		us.removeUser(us.getUserList().get(userId - 11));
//		if(us.getUserList().size() > 0)
//			this.id = us.getUserList().get(us.getUserList().size() - 1).getUserId() + 1;
	}

}