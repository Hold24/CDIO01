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
	public void testAddUser() throws DALException {
		
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
	public void testRemoveUser() throws DALException {
		us.addUser(new UserDTO());
		
		int expected = 1;
		int actual = us.getUserList().size();
		assertEquals(expected, actual);
		
		us.removeUser(us.getUser(0));
		
		expected = 0;
		actual = us.getUserList().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test() throws DALException {
		us.addUser(new UserDTO());
		us.getUser(0).setUserName("Niklas");
		us.addUser(new UserDTO());
		us.getUser(1).setUserName("Sammy");
		us.addUser(new UserDTO());
		us.getUser(2).setUserName("Einar");
		us.addUser(new UserDTO());
		us.getUser(3).setUserName("Mads");
		
		int i = 0;
		if (us.getUserList().get(0))
		us.getUserList().get(0);
	}

}
