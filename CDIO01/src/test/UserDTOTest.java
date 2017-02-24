package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.UserDTO;

public class UserDTOTest {

	UserDTO ud;
	
	@Before
	public void setUp() throws Exception {
		ud = new UserDTO();
	}

	@After
	public void tearDown() throws Exception {
		ud = null;
	}

	@Test
	public void positiveTestSetName() {
		//Expects null at first
		String expected = null;
		String actual = ud.getUserName();
		assertEquals(expected, actual);
		
		//Now the name has been manipulated, and we therefor expects, the name we set it to.
		expected = "Niklas";
		ud.setUserName("Niklas");
		actual = ud.getUserName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void positiveTestSetCPR() {
		//Expects null at first
		String expected = null;
		String actual = ud.getCpr();
		assertEquals(expected, actual);
		
		//Expects the value we set it to.
		expected = "151192-2257";
		ud.setCpr("151192-2257");
		actual = ud.getCpr();
		assertEquals(expected, actual);
	}
	 
	@Test
	public void positiveTestAddRole() {
		//Expects 0 in size.
		int expected = 0;
		int actual = ud.getRoles().size();
		assertEquals(expected, actual);
		
		//Expects 1 in size, therefor 1 role.
		ud.addRole("Admin");
		expected = 1;
		actual = ud.getRoles().size();
		assertEquals(expected, actual);
		
		//Tests what role there are.
		String expectedS = "Admin";
		String actualS = ud.getRoles().get(0).toString();
		assertEquals(expectedS, actualS);
	}
	
	@Test
	public void positiveTestRemoveRole() {
		ud.addRole("Admin");
		
		int expected = 1;
		int actual = ud.getRoles().size();
		assertEquals(expected, actual);
		
		ud.removeRole("Admin");
		expected = 0;
		actual = ud.getRoles().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void positiveTestSetInitials() {
		String expected = null;
		String actual = ud.getIni();
		assertEquals(expected, actual);
		
		ud.setIni("NB");
		expected = "NB";
		actual = ud.getIni();
		assertEquals(expected, actual);
	}
	
	@Test
	public void positiveTestSetPassword() {
		String expected = "ABCdef123";
		ud.setPassword("ABCdef123");
		String actual = ud.getPassword();
		assertEquals(expected, actual);
	}

}
