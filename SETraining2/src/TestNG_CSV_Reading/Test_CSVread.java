package TestNG_CSV_Reading;

import org.testng.annotations.Test;

public class Test_CSVread {

	
	@Test (dataProvider="getDataFromCSV", dataProviderClass=DataProviderClass.class)
	public void testNgTest (String one,String two,String three) {
		System.out.println(one + " " + two + " " + three);
		
	}
}
