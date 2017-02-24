package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.IUserDAO.DALException;
import entity.UserDTO;
import entity.UserStore;

public class UserStoreTest {

	UserStore us;
	
	@Before
	public void setUp() throws Exception {
		us = new UserStore();
	}

	@After
	public void tearDown() throws Exception {
		us = null;
	}

	@Test
	public void positiveTestAddUser() throws DALException {
		
		//Expects size to be 0, because it is empty.
		int expected = 0;
		int actual = us.getUserList().size();
		assertEquals(expected, actual);
		
		us.addUser(new UserDTO());
		//Now we expect size to be 1.
		expected = 1;
		actual = us.getUserList().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void positiveTestRemoveUser() throws DALException {
		us.addUser(new UserDTO());
		
		int expected = 1;
		int actual = us.getUserList().size();
		assertEquals(expected, actual);
		
		us.removeUser(us.getUserList().get(0));
		
		expected = 0;
		actual = us.getUserList().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test() throws DALException {
		us.addUser(new UserDTO());
		us.getUserList().get(0).setUserName("Niklas");
		us.addUser(new UserDTO());
		us.getUserList().get(1).setUserName("Sammy");
		us.addUser(new UserDTO());
		us.getUserList().get(2).setUserName("Einar");
		us.addUser(new UserDTO());
		us.getUserList().get(3).setUserName("Mads");
		us.addUser(new UserDTO());
		us.getUserList().get(4).setUserName("Michael");
		
		int i = 0;
		assertEquals("Niklas", us.getUserList().get(i).getUserName());
		i++;
		assertEquals("Sammy", us.getUserList().get(i).getUserName());
		i++;
		assertEquals("Einar", us.getUserList().get(i).getUserName());
		i++;
		assertEquals("Mads", us.getUserList().get(i).getUserName());
		i++;
		assertEquals("Michael", us.getUserList().get(i).getUserName());
	}

}
