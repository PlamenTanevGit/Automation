package tPOM_tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tPOM.CreateAccountPage;
import tPOM.LoginPage;
 

public class Test2 extends Utilities.DriverFactory{
	
	String URL = "http://sdettraining.com/trguitransactions/NewAccount.aspx";
	
	@BeforeClass
	public void setUp () {
		driver= Utilities.DriverFactory.open2(10, driver, "chrome");
		
	} 
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(URL);

	}
	
	
	@Test
	 
	public void testSuccessLogin ( ) {
		
		CreateAccountPage.Name(driver).sendKeys("Plamen");
		CreateAccountPage.Email(driver).sendKeys("email@emai.com");
		CreateAccountPage.Phone(driver).sendKeys("12212121212");
		CreateAccountPage.Male(driver).click();
		CreateAccountPage.Password(driver).sendKeys("505050");
		CreateAccountPage.VerifyPassword(driver).sendKeys("505050");
		CreateAccountPage.countrySelect(driver, "Germany");
		CreateAccountPage.subscriptionSelect(driver, "Weekly");
		CreateAccountPage.Submit(driver).click();
		
		//Utilities.Utils.verifyEqualTexts(findElement("id", "MainContent_lblTransactionResult"), "Customer information added successfully");
		 
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
