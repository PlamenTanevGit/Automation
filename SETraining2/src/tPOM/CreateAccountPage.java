package tPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CreateAccountPage  {

	
	public static WebElement element = null;

	/**
	 * Returns the FilterNavigator
	 * @param driver
	 * @return
	 */
	public static WebElement NavBar (WebDriver driver) {
		element = Utilities.Utils.findElement("xpath", "//input[@id='filter']");
		return element;
	}

	

}
