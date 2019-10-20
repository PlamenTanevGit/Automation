package tPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {

	public static WebElement element = null;

	/**
	 * Returns the Name
	 * 
	 * @param driver
	 * @return
	 */
	public static WebElement Name(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_txtFirstName']");
		return element;
	}

	public static WebElement Email(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_txtFirstName']");
		return element;
	}

	public static WebElement Phone(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_txtFirstName']");
		return element;
	}

	public static WebElement Male(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_Male']");
		return element;
	}

	public static WebElement Female(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_Female']");
		return element;
	}

	public static WebElement Password(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_txtPassword']");
		return element;
	}

	public static WebElement VerifyPassword(WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_txtVerifyPassword']");
		return element;
	}

	public static void countrySelect(WebDriver driver, String country) {
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(country);
	}

	public static void subscriptionSelect(WebDriver driver, String subscription) {

		if (subscription.equalsIgnoreCase("Weekly")) {
			driver.findElement(By.xpath("//input[@id='MainContent_checkWeeklyEmail']")).click();

		} else if (subscription.equalsIgnoreCase("Montly")) {
			driver.findElement(By.id("MainContent_checkMonthlyEmail")).click();

		} else {
			driver.findElement(By.id("MainContent_checkUpdates")).click();

		}
	}
	
	public static WebElement Submit (WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_btnSubmit']");
		return element;
	}

}
