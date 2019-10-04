package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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

public class Utils extends Utilities.DriverFactory{
	public static final int TIMEOUT = 10;
	public static WebElement element = null;
		public static JavascriptExecutor js;
	
	
	
	
	public static void searchAndSelectInNavbar (String searchText, WebElement searchedElement ) {
		findField("xpath", "//input[@id='filter']", searchText);
		click(searchedElement);
		} 
	
	
	
	
	public static void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);

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
			return  driver.findElement(By.id(locatorPath));
						//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locatorPath))));
		

		case "xpath":
			return //driver.findElement(By.xpath(locatorPath));
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
			
		case "name":
			return  driver.findElement(By.name(locatorPath));
					//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locatorPath))));
		

		case "classname":
			return  driver.findElement(By.className(locatorPath));
					//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(locatorPath))));
		

		case "cssselector": 
			return driver.findElement(By.cssSelector(locatorPath));
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locatorPath))));
		 

		case "linktext":
			return driver.findElement(By.linkText(locatorPath));
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locatorPath))));
		 

		case "tagname":
			return driver.findElement(By.tagName(locatorPath));
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(locatorPath))));
		 

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
	
	public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
		Date d = new Date();
		String fileName1 = d.toString().replace(":", "_").replace(" ", "_");
		fileName = fileName+fileName1+ ".png";
		String directory = System.getProperty("user.dir") + "\\screenshots\\";
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
	}
	
	

}
