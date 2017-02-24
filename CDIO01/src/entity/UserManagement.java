package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class UserManagement implements IUserDAO {

	UserStore us = new UserStore();
	MySQLAccess sql = new MySQLAccess();
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

		return us.getUserList();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		user.setPassword(pg.createPassword());
		us.addUser(user);

	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		//Update user


		//		switch (TUI.updateMenu()) {
		//		case 1:
		//			user.setUserName(TUI.userName());
		//			break;
		//		case 2:
		//			TUI.nextLine();
		//			user.setCpr(TUI.userCPR());
		//			break;
		//		case 3: 
		//			if (TUI.changeUserRole() == 1) {
		//				user.roleRemover(TUI.removeRole(user.getRoles()));
		//			}
		//			else {
		//				boolean hasRole = true;
		//				do {
		//					String role = TUI.addRole();
		//					if (!user.getRoles().contains(role)){
		//						user.addRole(role);
		//						hasRole = false;
		//					}
		//					else
		//						TUI.hasRole();
		//				} while (hasRole);
		//			}
		//			break;
		//		case 4: 
		//			TUI.nextLine();
		//			user.setIni(TUI.userInitials());
		//			break;
		//		case 5: 
		//			user.setPassword(TUI.userPassword());
		//			break;
		//		default:
		//			break;
		//		}

	}


	@Override
	public void deleteUser(int userId) throws DALException {

		for (int i = 0; i < us.getUserList().size(); i++) {
			if (userId == us.getUserList().get(i).getUserId())
				us.removeUser(us.getUserList().get(i));
		}
	}

	public void loadUsersText() throws DALException{
		UserStore userStore = new UserStore();
		ObjectInputStream oIS = null;
		try {
			FileInputStream fIS = new FileInputStream("UserList.txt");
			oIS = new ObjectInputStream(fIS);
			Object inObj = oIS.readObject();
			if (inObj instanceof UserStore) {
				userStore = (UserStore) inObj;
			}
			else {
				throw new DALException("Wrong object in file");
			}
		} 
		catch (FileNotFoundException e) {
			//No problem - just returning empty userstore
		} 
		catch (IOException e) {
			throw new DALException("Error while reading disk!", e);
		} 
		catch (ClassNotFoundException e) {
			throw new DALException("Error while reading file - Class not found!", e);
		} 
		finally {
			if (oIS != null){
				try {
					oIS.close();
				}
				catch (IOException e) {
					throw new DALException("Error closing pObjectStream!", e);
				}
			}
		}
		this.us = userStore;
	}
	
	public UserStore getUserStore(){
		return this.us;
	}

	public void saveUsersText(UserStore users) throws DALException {
		ObjectOutputStream oOS = null;
		try {
			FileOutputStream fOS = new FileOutputStream("UserList.txt");
			oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(users);
		}
		catch (FileNotFoundException e) {
			throw new DALException("Error locating file", e);
		}
		catch (IOException e) {
			throw new DALException("Error writing to disk", e);
		}
		finally {
			if (oOS != null) {
				try {
					oOS.close();
				}
				catch (IOException e) {
					throw new DALException("Unable to close ObjectStream", e);
				}
			}
		} 
	}
	
	public void loadUsersDB() throws Exception{
		sql.readDB();
	}

}