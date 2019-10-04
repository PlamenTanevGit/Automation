import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 *  this test will verify the Success creating of account using random generated data and methods for page actions.
 */
public class _3rdTest_with_methods_ench {
	static WebDriver driver;
	public static WebElement element;
	public static JavascriptExecutor js;
	public static final int TIMEOUT = 10;

	public static void main(String[] args) {

		// ADD STRINGS with Data
		String baseUrl = "http://www.sdettraining.com/trguitransactions/Accountmanagement.aspx";
		String password = randomString()+"Password";
		String verifyPassword = password;
		String expectedTitle = "SDET Training | Register New Account";

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\wdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // maximize the window
		driver.get(baseUrl);
		WebElement CreateAccount = findElement("xpath", "//a[@class='btn btn-default']");
		CreateAccount.click();
		
		// Define WebElements using predefinde methods
		WebElement SubmitBtn = findElement("xpath", "//input[@id='MainContent_btnSubmit']");
		WebElement ResetBtn = findElement("name", "ctl00$MainContent$btnReset");
		WebElement MaleButton = findElement("xpath", "//*[@id='MainContent_Male']");
		WebElement Female = findElement("xpath", "//label[@for='MainContent_Female']");
	
		pageTitleVerify(expectedTitle);
			
		findField("name", "ctl00$MainContent$txtFirstName", "Name"+randomString());
		findField("id", "MainContent_txtEmail", randomString() + "@mail.com");
		findField("xpath", ".//*[@id='MainContent_txtHomePhone']", randomString2());
		findField("xpath", "//*[@id='MainContent_txtPassword']", password);
		findField("xpath", "//*[@id='MainContent_txtVerifyPassword']", verifyPassword);
		genderSelect(randomGender(), MaleButton, Female);
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(randomCountry());
		subscriptionSelect(randomSubscriptions());
		SubmitBtn.click();

		verifyEqualTexts(findElement("id", "MainContent_lblTransactionResult"), "Customer information added successfully");
		
		driver.quit();

	}
	

//	WebElement nameField = findElement("name", "ctl00$MainContent$txtFirstName");
//	nameField.sendKeys("Name"+randomString());
	
	
	//  Methods
	public static void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);

	}
	
	public static void subscriptionSelect (String subscription) {
		
		if (subscription.equalsIgnoreCase("Weekly")) {
			findElement("xpath", "//input[@id='MainContent_checkWeeklyEmail']").click();
		} else if (subscription.equalsIgnoreCase("Montly")) {
			findElement("id", "MainContent_checkMonthlyEmail").click();
		} else {
			findElement("id", "MainContent_checkUpdates").click();
		}
	}
	
	public static void genderSelect (String gend, WebElement male, WebElement female) {
		if (gend.equalsIgnoreCase("Male")) {
			male.click();
		} else {
			female.click();
		}
	} 

	public static void verifyEqualTexts(WebElement element, String expected) {

		Assert.assertEquals(element.getText(), expected);
	
		System.out.println("Text : " + element.getText() + " -  is Verified");

	}

	public static WebElement findElement(String locatorType, String locatorPath) {
		try {
			element = returnElement(locatorType, locatorPath);
			element.isDisplayed();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return element;
	}

	public static WebElement findField(String locatorType, String locator, String value) {
		try {
			element = returnElement(locatorType, locator);
			element.isDisplayed();
			HighLightElement(element);
			element.sendKeys(value);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return element;
	}

	public static void HighLightElement(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
		try {
			Thread.sleep(15);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}

	public static WebElement returnElement(String locatorType, String locatorPath) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		switch (locatorType.toLowerCase()) {
		case "id":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locatorPath))));
		// driver.findElement(By.id(locatorPath));

		case "xpath":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
			
		case "name":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locatorPath))));
		// driver.findElement(By.name(locatorPath));

		case "classname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(locatorPath))));
		// driver.findElement(By.className(locatorPath));

		case "cssselector":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locatorPath))));
		// driver.findElement(By.cssSelector(locatorPath));

		case "linktext":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locatorPath))));
		// driver.findElement(By.linkText(locatorPath));

		case "tagname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(locatorPath))));
		// driver.findElement(By.tagName(locatorPath));

		default:
			throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
		}

	}

	public static void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		js = (JavascriptExecutor) driver;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			// element.click();
			// Reporter.log("Successfully click on element " + element.getText());
		} catch (StaleElementReferenceException e) {

			e.printStackTrace();

		}
	}

	public static String randomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomString = "";
		int lenght = 8;

		Random rand = new Random();

		char[] text = new char[lenght];

		for (int i = 0; i < lenght; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}

	public static String randomString2() {
		String characters = "1234567890";
		String randomString = "";
		int lenght = 8;

		Random rand = new Random();

		char[] text = new char[lenght];

		for (int i = 0; i < lenght; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}

	public static String randomCountry() {

		String[] arr = { "United States", "Albania", "Australia", "Belgium", "Canada", "India", "Germany" };
		Random r = new Random();

		int randomNumber = r.nextInt(arr.length);

		return (arr[randomNumber]);

	}
	
	public static String randomGender() {

		String[] arr = {"Male","Female" };
		Random r = new Random();

		int randomGender = r.nextInt(arr.length);

		return (arr[randomGender]);

	}
	
	public static String randomSubscriptions() {

		String[] arr = {"Weekly","Montly","Occasional" };
		Random r = new Random();

		int randomSubscriptions = r.nextInt(arr.length);

		return (arr[randomSubscriptions]);

	}
}
