package Utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProvider1 {
	
	/*
	 * Data provider parsing the data in the @Test method using arguments in the test methof
	 */
	@DataProvider(name="testData")
	public String[][] testData() {
		String[][] testData = { 
				{ "Ellie Prynne", "ep@testemail.com", "ep1password"}, 
				{ "Shawn Thompson", "sw@testemail.com", "sw2password"}, 
				{ "Michael Lane", "ml@testemail.com", "ml3password"}, 
				{ "Janelle Von", "jv@testemail.com", "jv4password"}, 

		};
		
		return testData;
		
	}
	
	
	@DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(Method m){
        if(m.getName().equalsIgnoreCase("loginTest")){
        return new Object[][] {
        	{ "Ellie Prynne", "ep@testemail.com", "ep1password"}, 
			{ "Shawn Thompson", "sw@testemail.com", "sw2password"}, 
          };}
        else{
        return new Object[][] {
        	{ "Michael Lane", "ml@testemail.com", "ml3password"}, 
			{ "Janelle Von", "jv@testemail.com", "jv4password"}, 
            };}       
    }
	

}
