package tPOM_tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tPOM.LoginPage;
 

public class Test1 extends Utilities.DriverFactory{
	
	String URL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
	
	@BeforeClass
	public void setUp () {
		driver= Utilities.DriverFactory.open2(10, driver, "chrome");
		
	} 
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(URL);

	}
	
	
	@Test
	@Parameters({"Username", "Password"})
	public void testSuccessLogin (String Username, String Password) {
		
		LoginPage.Login( Username, Password);
		Utilities.Utils.verifyEqualTexts(Utilities.Utils.findElement("cssSelector", "small[id='conf_message']"), "Logged in successfully");
		 
	}
	
	
	@AfterMethod
	public void tearDown (Method m) {
		try {
			Utilities.Utils.takeScreenshot(driver, m.getName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		driver.quit();
	}
	

}
