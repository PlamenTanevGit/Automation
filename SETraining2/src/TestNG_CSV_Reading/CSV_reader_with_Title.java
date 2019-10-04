package TestNG_CSV_Reading;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class CSV_reader_with_Title {
	public static String[][] getCSVData(String path, boolean isTitleRowAvailable) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] csvLine;

		//boolean isTitleRowAvailable;
		if (isTitleRowAvailable == true) {
			
		}
		csvLine = reader.readNext();

		ArrayList<ArrayList<String>> listOfCsvRows = new ArrayList<ArrayList<String>>();

		while ((csvLine = reader.readNext()) != null) {
			int csvLineLength = csvLine.length;
			ArrayList<String> csvRow = new ArrayList<String>(csvLineLength);
			for (int i = 0; i < csvLineLength; i++) {
				csvRow.add(csvLine[i]);
			}
			listOfCsvRows.add(csvRow);
		}
		reader.close();
		String[][] data = convertToStringArray(listOfCsvRows);
		return data;
	}

	private static String[][] convertToStringArray(ArrayList<ArrayList<String>> arrayList) {
		int dataColumns = arrayList.size();
		int dataRows = arrayList.get(0).size();
		String[][] data = new String[dataColumns][dataRows];

		for (int i = 0; i < dataColumns; i++) {
			ArrayList<String> arrayListRow = new ArrayList<String>(dataColumns);
			arrayListRow = arrayList.get(i);

			for (int j = 0; j < arrayListRow.size(); j++) {
				data[i][j] = arrayListRow.get(j).toString();
			}
		}
		return data;
	}
}
