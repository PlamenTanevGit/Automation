package TestNG;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.DataProvider1;


/*
 *  this test will use @DataProvider for data parsing as a String.
 */

public class _9th_TestNg_DataProvider2 {
	public JavascriptExecutor js;
	public final int TIMEOUT = 10;
	public WebElement element;
	WebDriver driver;
	public WebElement emaill, passwordd, LogInn, unsuccessLoginMsg;
	String URL = "http://www.sdettraining.com/trguitransactions/Accountmanagement.aspx";
	
	@BeforeTest
	public void setUp() {
		// 1 Define the WebDriver and Define WebElements;
		driver = Utilities.DriverFactory.open("firefox");
		// 2. Open the page:
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get(URL);
		//3. Define Web Elements method
		defieneWebElements();
		//4. page title verify
		pageTitleVerify("SDET Training | Account Management");
	}
	
	

	@Test(dataProvider = "testData",dataProviderClass =DataProvider1.class) 
	public void loginTest(String name, String email, String password) {
		driver.get(URL);
		//3. Define Web Elements method
		defieneWebElements();
		//4. page title verify
		pageTitleVerify("SDET Training | Account Management");
		// 3. Enter email:
		findField_(emaill, name);
			// 4. Enter password
		findField_(passwordd, password);
		// 5. Click on Login
		LogInn.click();
		
		// 6. Get unseccess Login Msg 
		verifyEqualTexts(unsuccessLoginMsg = findElement("id", "MainContent_lblTransactionResult"), "Invalid user name, try again!");

	}

	

	public void defieneWebElements() {
		emaill =findElement("name", "ctl00$MainContent$txtUserName");
		passwordd= findElement("name", "ctl00$MainContent$txtPassword");
		LogInn = driver.findElement(By.name("ctl00$MainContent$btnLogin"));
	}

	@AfterMethod
	public void afterMethod () {
		try {
			takeScreenshot(driver, "screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.close();
	}
	
	
	
	// Helpfull Methods
	
	public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
		Date d = new Date();
		String fileName1 = d.toString().replace(":", "_").replace(" ", "_");
		fileName = fileName+fileName1+ ".png";
		String directory = System.getProperty("user.dir") + "\\screenshots\\";
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
	}
	
		public void pageTitleVerify(String expected) {
			String actual = driver.getTitle();
			Assert.assertEquals(actual, expected);

		}

		public static void verifyEqualTexts(WebElement element, String expected) {

			Assert.assertEquals(element.getText(), expected);
			 
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

		public  WebElement findField(String locatorType, String locator, String value) {
			try {
				element = returnElement(locatorType, locator);
				element.isDisplayed();
				HighLightElement(element);
				element.clear();
				element.sendKeys(value);

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			return element;
		}
		
		public  WebElement findField_(WebElement element, String value) {
			try {
				element.isDisplayed();
				HighLightElement(element);
				element.clear();
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

	

	
}
