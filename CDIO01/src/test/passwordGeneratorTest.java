package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.passwordGenerator;

public class passwordGeneratorTest {

	passwordGenerator pg;
	
	@Before
	public void setUp() throws Exception {
		pg = new passwordGenerator();
	}

	@After
	public void tearDown() throws Exception {
		pg = null;
	}

	//Testing if our passwordgenerator is completely random.
	@Test
	public void testPasswordGenerator() {
		//Expects the password to be different all 1.000.000 times. It can be the same, but in our case, the risk of a password being the same is extremly low, and therefor not expected.
		int expected = 0, actual = 1000000;
		String s1 = pg.createPassword(), s2 = null;
		
		for (int i = 0; i < 999999; i++) {
			s2 = pg.createPassword();
			if (s1 != s2)
				actual--;
		}
	}

}
