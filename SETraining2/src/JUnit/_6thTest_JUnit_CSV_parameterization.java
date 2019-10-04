package JUnit;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
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
import org.testng.Reporter;

@RunWith(Parameterized.class) //1 . Add @RunWith(Parameterized.class) - this tells that we going to use Parameters in the Test.
public class _6thTest_JUnit_CSV_parameterization {

	public WebDriver driver;
	public static WebElement element;
	public static JavascriptExecutor js;
	public static final int TIMEOUT = 10;
	

	String baseUrl = "http://www.sdettraining.com/trguitransactions/Accountmanagement.aspx";
	String name;
	String email;
	String password ;
	String verifyPassword;
	String phoneNumber;
	String country;
	String gender;
	String Subscriptions ;
	String expectedTitle = "SDET Training | Register New Account";
	
/*
 * creating @Parameters reader for reading the CSV
 */
	@Parameters
	public static List<String[]> getTestData() throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		
		//creating Bufferreader for theCSV File
		BufferedReader file = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\testData\\dataForParsing_CSV.csv"));
		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();

		return records;
	}

	//Creating Constructor
	public _6thTest_JUnit_CSV_parameterization
	(String name, String email, String pass,String verifyPass, String phone, String country, String gender,String subsc ) {
		this.name=name;
		this.email=email;
		this.password=pass;
		this.verifyPassword=verifyPass;
		this.phoneNumber= phone;
		this.country=country;
		this.gender= gender;
		this.Subscriptions=subsc;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\wdriver\\chromedriver.exe");
	}

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // maximize the window
		driver.get(baseUrl);
	}

	

	@Test
	public void test() throws InterruptedException {

		WebElement CreateAccount = findElement("xpath", "//a[@class='btn btn-default']");
		CreateAccount.click();

		// title check with Assert.
		Assert.assertTrue(driver.getTitle().equals(expectedTitle));
		
		
		WebElement SubmitBtn = findElement("xpath", "//input[@id='MainContent_btnSubmit']");
		WebElement ResetBtn = findElement("name", "ctl00$MainContent$btnReset");
		WebElement MaleButton = findElement("xpath", "//*[@id='MainContent_Male']");
		WebElement Female = findElement("xpath", "//label[@for='MainContent_Female']");
		findField("name", "ctl00$MainContent$txtFirstName", name);
		findField("id", "MainContent_txtEmail", email);
		findField("xpath", ".//*[@id='MainContent_txtHomePhone']", phoneNumber);
		findField("xpath", "//*[@id='MainContent_txtPassword']", password);
		findField("xpath", "//*[@id='MainContent_txtVerifyPassword']", verifyPassword);

		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(country);

		if (gender.equalsIgnoreCase("Male")) {
			MaleButton.click();
		} else {
			Female.click();
		}

		if (Subscriptions.equalsIgnoreCase("Weekly")) {
			findElement("xpath", "//input[@id='MainContent_checkWeeklyEmail']").click();
		} else if (Subscriptions.equalsIgnoreCase("Montly")) {
			findElement("id", "MainContent_checkMonthlyEmail").click();
		} else {
			findElement("id", "MainContent_checkUpdates").click();
		}

		SubmitBtn.click();

		// Verification of Success Custommer added information !!!! - MOST IMPORTANT
		String actualMessage = findElement("id", "MainContent_lblTransactionResult").getText();
		String expectedMessage = "Customer information added successfully";
		
		Assert.assertTrue((actualMessage).equals(expectedMessage));
		System.out.println("CONFIRMATION:" + actualMessage);

	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	
	
	
	
	// Helpfull Methods
	public void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);

	}

	public void verifyEqualTexts(WebElement element, String expected) {

		Assert.assertEquals(element.getText(), expected);
		Reporter.log("Text : " + element.getText() + " - is Verified");
		System.out.println("Text : " + element.getText() + " -  is Verified");

	}

	public WebElement findElement(String locatorType, String locatorPath) {
		try {
			element = returnElement(locatorType, locatorPath);
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return element;
	}

	public WebElement findField(String locatorType, String locator, String value) {
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

	public void HighLightElement(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
		try {
			Thread.sleep(15);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}

	public WebElement returnElement(String locatorType, String locatorPath) {
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

	public void click(WebElement element) {
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

		String[] arr = { "Male", "Female" };
		Random r = new Random();

		int randomGender = r.nextInt(arr.length);

		return (arr[randomGender]);

	}

	public static String randomSubscriptions() {

		String[] arr = { "Weekly", "Montly", "Occasional" };
		Random r = new Random();

		int randomSubscriptions = r.nextInt(arr.length);

		return (arr[randomSubscriptions]);

	}

}
