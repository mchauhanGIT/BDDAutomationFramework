package net.prodigylabs.handlers;

import org.junit.Assert;

/** @author vaishali.katta */
public class VerificationHandler {
	
	public static void verifyTrue(boolean flag)
	{
		try {
			Assert.assertTrue(flag);			
		} catch (AssertionError e) {
			Assert.fail("False returned");			
		}
	}
	
	public static void verifyFalse(boolean flag)
	{
		try {
			Assert.assertFalse(flag);			
		} catch (AssertionError e) {
			Assert.fail("True returned");			
		}
	}

	public static void verifyEquals(String actual, String expected)	{
		try {
			Assert.assertEquals(actual, expected);			
		} catch (AssertionError e) {
			Assert.fail("Expected result is no same as actual result");			
		}
	}
	
	public static void verifyNotEquals(String actual, String expected)	{
		try {
			Assert.assertNotEquals(actual, expected);		
		} catch (AssertionError e) {
			Assert.fail("Expected result is no same as actual result");			
		}
	}
}
