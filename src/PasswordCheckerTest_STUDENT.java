
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Michael Meyers
 *
 */
public class PasswordCheckerTest_STUDENT {

	//Create test objects
	PasswordCheckerUtility pwCheck = new PasswordCheckerUtility();
	String passwordA, passwordB, passwordC, passwordD, passwordE, 
			passwordF, passwordG, passwordH, passwordI;
	
	@Before
	public void setUp() throws Exception {
		passwordA = "P4s5W0rd"; //Valid password, but weak
		passwordB = "Supercalifragalisticexpialidocious"; //Invalid, no digit
		passwordC = "he1lO"; //Invalid, too short
		passwordD = "Yelll0wSubMaRin3"; //Invalid, triple letters
		passwordE = "SUPERSECUR3"; //Invalid, no lower case letters
		passwordF = "1234567"; //Invalid, no letters
		passwordG = "onetwothre3"; //Invalid, no upper case
		passwordH = "Pas5Al1Checks"; //Valid and not weak
		passwordI = "sp3c!alCh#rs*()"; //Valid, strong, has special characters
	}

	@After
	public void tearDown() throws Exception {
		passwordA = null;
		passwordB = null;
		passwordC = null;
		passwordD = null;
		passwordE = null;
		passwordF = null;
		passwordG = null;
		passwordH = null;
		passwordI = null;
		pwCheck = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test(expected = PasswordCheckerUtility.LengthException.class)
	public void testIsValidPasswordTooShort()
	{
		assertTrue(pwCheck.isValidPassword(passwordA));
		assertFalse(pwCheck.isValidPassword(passwordC)); //Will throw exception
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test(expected = PasswordCheckerUtility.NoUpperAlphaException.class)
	public void testIsValidPasswordNoUpperAlpha()
	{
		assertTrue(pwCheck.isValidPassword(passwordA));
		assertFalse(pwCheck.isValidPassword(passwordG)); //Will throw exception
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test(expected = PasswordCheckerUtility.NoLowerAlphaException.class)
	public void testIsValidPasswordNoLowerAlpha()
	{
		assertTrue(pwCheck.isValidPassword(passwordA));
		assertFalse(pwCheck.isValidPassword(passwordE)); //Will throw exception
	}
	/**
	 * Test if the password is weak - meaning valid, but 10 or fewer characters in length
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertTrue(pwCheck.isWeakPassword(passwordA)); //Is weak
		assertFalse(pwCheck.isWeakPassword(passwordH)); //Is strong
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test(expected = PasswordCheckerUtility.InvalidSequenceException.class)
	public void testIsValidPasswordInvalidSequence()
	{
		assertTrue(pwCheck.isValidPassword(passwordA));
		assertFalse(pwCheck.isValidPassword(passwordD)); //Will throw exception
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test(expected = PasswordCheckerUtility.NoDigitException.class)
	public void testIsValidPasswordNoDigit()
	{
		assertTrue(pwCheck.isValidPassword(passwordA));
		assertFalse(pwCheck.isValidPassword(passwordB)); //Will throw exception
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(pwCheck.isValidPassword(passwordA)); //Valid, but weak
		assertTrue(pwCheck.isValidPassword(passwordH)); //Valid and strong
		assertTrue(pwCheck.isValidPassword(passwordI)); //Valid, strong, with special characters
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testValidPasswords() {
		//Add passwords to an ArrayList
		ArrayList<String> testArrayList = new ArrayList<>();
		testArrayList.add(passwordA);
		testArrayList.add(passwordB);
		testArrayList.add(passwordC);
		testArrayList.add(passwordD);
		testArrayList.add(passwordH);
		testArrayList.add(passwordI);
		
		//Create ArrayList for holding returned data
		ArrayList<String> returnList = new ArrayList<>();
		
		//Run validPasswords and return data to returnString
		returnList = pwCheck.validPasswords(testArrayList);
		String returnString = "";
		for(int i = 0; i < returnList.size(); i++) {
			returnString += returnList.get(i) + "\n";
		}
		
		//Check for invalid passwords and error codes
		assertTrue(returnString.contains("Supercalifragalisticexpialidocious The password must contain at least one digit"));
		assertTrue(returnString.contains("he1lO The password must be at least 6 characters long"));
		assertTrue(returnString.contains("Yelll0wSubMaRin3 The password cannot contain more than two of the same character in sequence"));
		
		//Check for no false positives among valid passwords
		assertFalse(returnString.contains("P4s5W0rd"));
		assertFalse(returnString.contains("Pas5Al1Checks"));
		assertFalse(returnString.contains("sp3c!alCh#rs*()"));
	}
	
}
