import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class _2ndTest_enchanced {
	static WebDriver driver;

	public static void main(String[] args) {

		// ADD STRINGS for test data
		String baseUrl = "http://www.sdettraining.com/trguitransactions/Accountmanagement.aspx";
		String name = "PlamenGeorgiev";
		String email = "pdizzle83@abv.bg";
		String password = "Plamen12365";
		String phoneNumber = "1234567890";
		String country = "Germany";
		String gender = "Male";
		String Subscriptions = "Weekly";

		String expectedTitle = "SDET Training | Register New Account";

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\wdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // maximize the window
		driver.get(baseUrl);
		WebElement createAccountBtn = driver.findElement(By.xpath("//a[@class='btn btn-default']"));
		createAccountBtn.click();
		String actualTittle = driver.getTitle();

		//Page Title check > if we don't get the right page title break the test
		if (actualTittle.equals(expectedTitle)) {

		} else {
			System.out.println(" page title is wrong quit the browser ");
			driver.quit();
		}
		// DEFINING WEB ELEMENTS
		WebElement nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		WebElement EmailElement = driver.findElement(By.id("MainContent_txtEmail"));
		WebElement PhoneNumberElement = driver.findElement(By.xpath(".//*[@id='MainContent_txtHomePhone']"));
		WebElement PasswordElement = driver.findElement(By.xpath("//*[@id='MainContent_txtPassword']"));
		WebElement VerifyPasswordElement = driver.findElement(By.xpath("//*[@id='MainContent_txtVerifyPassword']"));
		WebElement SubmitButton = driver.findElement(By.id("MainContent_btnSubmit"));
		WebElement ResetButton = driver.findElement(By.name("ctl00$MainContent$btnReset"));
		WebElement WeeklyEmail = driver.findElement(By.xpath("//input[@id='MainContent_checkWeeklyEmail']"));
		WebElement OccasionalUpdated = driver.findElement(By.id("MainContent_checkUpdates"));
		WebElement MountlyEmail = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		WebElement MaleButton = driver.findElement(By.xpath("//*[@id='MainContent_Male']"));
		WebElement Female = driver.findElement(By.xpath("//label[@for='MainContent_Female']"));

		// Test part using variables starts from here :
		nameElement.clear();
		nameElement.sendKeys(name);
		EmailElement.clear();
		EmailElement.sendKeys(email);
		PhoneNumberElement.clear();
		PhoneNumberElement.sendKeys(phoneNumber);
		PasswordElement.clear();
		PasswordElement.sendKeys(password);
		VerifyPasswordElement.clear();
		VerifyPasswordElement.sendKeys(password);
		
		// check box mark - depend on the provided value 
		if (gender.equalsIgnoreCase("Male")) {
			MaleButton.click();
		}
		else {
			Female.click();
		}
		
		// Drop down Menu - selection
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(country);

		// mark Subscriptions - dependent on provided value
		if (Subscriptions.equalsIgnoreCase("Weekly")) {
			WeeklyEmail.click();
		}else if (Subscriptions.equalsIgnoreCase("Montly")) {
			MountlyEmail.click();
		}
		else {
			OccasionalUpdated.click();
		}

		// verification Submit and Reset are disaplayed if both or some are not displayed -error message and quit
		if (SubmitButton.isDisplayed() && ResetButton.isDisplayed()) {
			System.out.println("Submit and Reset are Displayed");
			
		} else if (SubmitButton.isDisplayed() && !ResetButton.isDisplayed()) {
			System.out.println("Submit is Displayed  Reset is NOT Displayed");
		
		} else if (!SubmitButton.isDisplayed() && ResetButton.isDisplayed()) {
					System.out.println("Submit is NOT Displayed, Reset is Displayed");
					
		} else {
			System.out.println("Submit is NOT Displayed, Reset is NOT Displayed");
			
		}
		// click on Submit button 
		SubmitButton.click();
		
		// Verification of Success Custommer added information 
		String actualMessage = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		String expectedMessage = "Customer information added successfully";
		// assert actual=expected.
		Assert.assertTrue((actualMessage).equals(expectedMessage));
			System.out.println("CONFIRMATION:" + actualMessage);
			
		driver.quit();
		
	}

}
