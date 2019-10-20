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

public class _7thTest_TestNG_annotation {
	
	@Test (testName="Test1))")
	public void test1() {
		System.out.println("Test1");
	}
	
	//Grouping
	@Test(dependsOnGroups={"myGroup"})
	public void test2() {
		System.out.println("Test2 - depends on myGroup");
	}
	
	
	@Test(groups={"myGroup"}, dependsOnMethods={"test4"})
	public void test3(){
		System.out.println("Test3 withing group, depends on test4");
	}
	
	@Test(groups={"myGroup"})
	public void test4(){
		System.out.println("Test4 withing group");
	}
	@Test (priority = 0)
	public void test5 () {
		System.out.println("test5");
	}
	
	@Test (alwaysRun=true, dependsOnMethods = {"test1"})
	public void test6 () {
		System.out.println("test6");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("beforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite");
	}
	
	@BeforeGroups(groups={"myGroup"})
	public void beforeGroup(){
		System.out.println("beforeGroup");
	}
	
	@AfterGroups(groups={"myGroup"})
	public void afterGroup(){
		System.out.println("afterGroup");
	}

}