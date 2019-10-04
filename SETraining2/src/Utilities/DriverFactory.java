package Utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
 // This method return WebDriverobject
	
	public static WebDriver driver;
	
	
	public static WebDriver open(String browserType) {
		if(browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\wdriver\\geckodriverNew.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			
			else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\wdriver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			}
		return driver;
		}
	
	public static WebDriver open2 (int seconds, WebDriver driver, String browserType) {
		driver = open(browserType);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		return driver;
	}
}
	
		

