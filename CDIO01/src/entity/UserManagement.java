package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public void showUsersDB() throws Exception{
		sql.readDB();
	}
	
	public UserDTO loadUserDB(int id) throws SQLException{
		UserDTO user = new UserDTO();
		String[] userValues = new String[5];
		userValues = sql.loadUser(id);
		user.setUserId(id);
		user.setUserName(userValues[0]);
		user.setIni(userValues[1]);
		user.setPassword(userValues[2]);
		user.setCpr(userValues[3]);
		List<String> roles = new ArrayList<String>();
		for(int i = 0; i < userValues[4].split(", ").length; i++){
			if(userValues[4].split(", ")[i].contains("Admin"))
				roles.add("Admin");
			else if(userValues[4].split(", ")[i].contains("Pharmacist"))
				roles.add("Pharmacist");
			else if(userValues[4].split(", ")[i].contains("Foreman"))
				roles.add("Foreman");
			else if(userValues[4].split(", ")[i].contains("Operator"))
				roles.add("Operator");
		}
		user.setRoles(roles);
		
		return user;
	}
	
	public boolean checkForUser(int id) throws Exception {
		return sql.checkForUser(id);
	}
	
	public void writeUserDB(UserDTO user) throws SQLException {
		int id;
		String[] values = new String[5];
		id = user.getUserId();
		values[0] = user.getUserName();
		values[1] = user.getIni();
		values[2] = user.getPassword();
		values[3] = user.getCpr();
		values[4] = user.getRoles().toString();
		sql.writeUser(values, id);
	}
	
	public String generatePassword() {
		return pg.createPassword();
	}
	
	public void deleteUserDB(int id) throws SQLException {
		sql.deleteUser(id);
	}
	
	public void updateDBUser(String newValue, String column, int id) throws SQLException{
		sql.updateUser(newValue, column, id);		
	}

}