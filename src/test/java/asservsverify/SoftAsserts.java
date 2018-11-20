package asservsverify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		int i = 100;
		int j = 200;
		
		System.out.println("First Assertion: ");
		softAssert.assertEquals(i, j);
		
		System.out.println("Second Assertion: ");
		softAssert.assertNotEquals(i, j);
		
		System.out.println("third assertion:");
		softAssert.assertTrue(i > j);
		
		softAssert.assertAll(); // assertAll will tell us which one was passed and which one was failed 
	}
}
