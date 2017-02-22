package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import entity.IUserDAO.DALException;

public class UserStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2598715957098613225L;
	private ArrayList<UserDTO> userList;

	public UserStore() {
		userList = new ArrayList<UserDTO>();
	}

	public UserStore loadUsersText() throws DALException{
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
		return userStore;
	}

	private void saveUsersText(UserStore users) throws DALException {
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

	public void loadUsersDB(){

	}
	public void saveUsersDB(){

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
