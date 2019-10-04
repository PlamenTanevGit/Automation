import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class _1stSimpleTest {
	// initialise the Webdriver
	static WebDriver driver;
	static int sec = 10;
	static String URL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";

	public static void main(String[] args) {
		// calling the Chrome driver
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\wdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		// set up Wait - implicit
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		// window maximize
		driver.manage().window().maximize(); 
		

		// 1. Open the URL
		driver.get(URL);

		// 2. find by 'xpath' and click on the 'Create Account'
		driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();

		// 3. using getTitle and check the page title - asserting the page title is
		// correct.
		String actualCreateAccountTittle = driver.getTitle();
		String expectedTitle = "SDET Training | Register New Account"; // page title from the DOM

		// Title check > if we don't get the right page title break the test
		if (actualCreateAccountTittle.equals(expectedTitle)) {
			System.out.println("Expeted title = Actual title"); // mesage for success

		} else {
			System.out.println(" page title is wrong quit the browser ");
			driver.quit();
		}
		
		// name - CSS selector example
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$txtHomePhone']")).sendKeys("test");
		
		driver.findElement(By.name("ctl00$MainContent$txtFirstName")).sendKeys("test1");
		System.out.println("Name is filled");
		// email-CSS selector example
		driver.findElement(By.cssSelector("#MainContent_txtEmail")).sendKeys("test@email.com");
		System.out.println("Password is filled");
		// phone
		driver.findElement(By.id("MainContent_txtHomePhone")).sendKeys("123456799");
		System.out.println("Phone is filled");
		// gender
		driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
		System.out.println("Male is clicked");
		// password
		driver.findElement(By.xpath("//*[@id='MainContent_txtPassword']")).sendKeys("testPass1");
		System.out.println("Password is entered");
		// verify pass
		driver.findElement(By.xpath("//*[@id='MainContent_txtVerifyPassword']")).sendKeys("testPass1");
		System.out.println("Verify Password is entered");

		try {
			// country - using Select -by visible text (find by id)
			new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText("Germany");

			Thread.sleep(1500);
			// country - using Select -by index (find by id)
			new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByIndex(3);

			Thread.sleep(1500);
			// country - using Select -by index (find by id)
			new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByValue("Denmark");

		} catch (Exception e) {
			System.out.println("unable to find the dropdown");
			e.printStackTrace();
		}

		// select 'Weekly Email'
		driver.findElement(By.id("MainContent_checkWeeklyEmail")).click();

		// check if Submit and Reset are Displayed - using isDisplaye method
		if (driver.findElement(By.name("ctl00$MainContent$btnSubmit")).isDisplayed()
				&& driver.findElement(By.name("ctl00$MainContent$btnReset")).isDisplayed()) {
			System.out.println("Submit and Reset are Displayed");
		} else if (driver.findElement(By.name("ctl00$MainContent$btnSubmit")).isDisplayed()
				&& !driver.findElement(By.name("ctl00$MainContent$btnReset")).isDisplayed()) {
			System.out.println("Submit is Displayed  Reset is NOT Displayed");
		} else if (!driver.findElement(By.name("ctl00$MainContent$btnSubmit")).isDisplayed()
				&& driver.findElement(By.name("ctl00$MainContent$btnReset")).isDisplayed()) {
			System.out.println("Submit is NOT Displayed, Reset is Displayed");
		} else {
			System.out.println("Submit is NOT Displayed, Reset is NOT Displayed");
		}

		// click on Submit
		driver.findElement(By.name("ctl00$MainContent$btnSubmit")).click();
		System.out.println("click on Submit");

		// validation of success Custommer adding by message - using Assert
		String actualMessage = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		String expectedMessage = "Customer information added successfully";
		Assert.assertTrue((actualMessage).equals(expectedMessage));
		System.out.println("Success ccount Creation");

		driver.quit();
	}

}
