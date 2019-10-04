package tPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage  {

	
	public static WebElement element = null;

	/**
	 * Returns the Create Account Button
	 * @param driver
	 * @return
	 */
	public static WebElement CreateAccountBtn (WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//a[@class='btn btn-default']");
		return element;
	}

	/**
	 * Returns the Email Address field
	 * @param driver
	 * @return
	 */
	
	public static WebElement Email (String text) {
		element = Utilities.Utils.findField("xpath", "//input[@id='MainContent_txtUserName']", text);
		return element;
	}
	/**
	 * Returns  Password filed
	 * @param driver
	 * @return
	 */
	public static WebElement Password (String text) {
		element = Utilities.Utils.findField("xpath", "//input[@id='MainContent_txtPassword']", text);
		return element;
	}
	
	/**
	 * Returns the LoginBtn
	 * @param driver
	 * @return
	 */
	public static WebElement LoginBtn () {
		element = Utilities.Utils.findElement("xpath", "//input[@id='MainContent_btnLogin']");
		return element;
	}
	/**
	 * Login
	 * @param driver, @param String, @param String
	 * @return
	 */
	public static void Login (String username, String password) {
		Email(username);
		Password( password);
		LoginBtn().click();
	} 
}
