package TestNG;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class _7thTest_TestNG_annotation2 {
	

	@Test (priority = 0)
	public void test5 () {
		System.out.println("test5");
	}
	
	@Test (priority = 3)
	public void test4 () {
		System.out.println("test4");
	}
	
	@Test (priority = 1)
	public void test1 () {
		System.out.println("test1");
	}
	
	@Test (priority = 2)
	public void test3 () {
		System.out.println("test3");
	}
	
	@Test (priority = 4)
	public void test2 () {
		System.out.println("test2");
	}

}