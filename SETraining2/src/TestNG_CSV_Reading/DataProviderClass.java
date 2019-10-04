package TestNG_CSV_Reading;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "getDataFromCSV")
	public static Object[][] getDataFromCSV () throws Exception {
		return TestNG_CSV_Reading.CSV_reader_with_Title.getCSVData(System.getProperty("user.dir") + "\\testData\\sampleCSVdata.csv", false);
	}
	
	
	
}
