package org.lenskart.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utilities.ExcelLib_POI;
import org.utilities.JsonFlattener;
import org.utilities.JsonUtil;

public class TestAPI {

	private static final Logger log = Logger.getRootLogger();
	
	public static void main(String args[]) throws IOException, JSONException {
		// System.out.println(JsonPayloadGenerator.getPaymentMethods_msite_payload);
		// System.out.println(JsonPayloadGenerator.aa);
		// new
		// JsonUtil().convert_json_textfile_to_object("/Users/fcak17801/eclipse/workspace/Payments-API/API_Payload/getPaymentMethods_mobilesite.json");

	}

	@Test(enabled = false)
	void test_json_flattener() throws JSONException, IOException {

		JsonFlattener parser = new JsonFlattener();
		JSONObject jsonObj = JsonUtil.convert_json_textfile_to_object(
				"/Users/fcak17801/eclipse/workspace/Payments-API/json_payload_files/jsonExample1.json");
		Map<String, String> flatJsonList = parser.parse(jsonObj);

		System.out.println(flatJsonList);

	}

	@Test(enabled = false)
	void test_data_provider() {
		ExcelLib_POI xl = new ExcelLib_POI(
				"/Users/fcak17801/eclipse/workspace/Payments-API/excel_payload_files/MakePaymentAPI.xlsx",
				"makePaymentRequestParams");
		// xl.columnIndexMap.forEach((k,v)->System.out.println("Key : " + k + "
		// Value : " + v));
		// xl.columnIndexMap.forEach((k,v)-> data[]);

		int numberOfRows = xl.getRowCount();
		int numberOfColums = xl.getColumnCount(0);

		System.out.println("Counts of row , columns " + numberOfRows + ", " + numberOfColums);

		String[][] data = new String[numberOfRows][numberOfColums];
		String x = "";
		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 0; j < xl.getColumnCount(i); j++) {
				NameValuePair value_pair = xl.getColumnValuePair(j, i);
				x = value_pair.getName() + ":" + value_pair.getValue();

				if (x != null && !x.equals("")) {
					data[i][j] = x;
				}
			}
		}

		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 0; j < xl.getColumnCount(i); j++) {
				System.out.println(data[i][j]);

			}
		}

	}

	@Test(enabled = false, dataProvider = "payloadData")
	void test_payload_creator(String a, String b) {

	}

	@DataProvider
	public Object[][] payloadData() {

		ExcelLib_POI xl = new ExcelLib_POI(
				"/Users/fcak17801/eclipse/workspace/Payments-API/excel_payload_files/MakePaymentAPI.xlsx",
				"makePaymentRequestParams");
		// xl.columnIndexMap.forEach((k,v)->System.out.println("Key : " + k + "
		// Value : " + v));
		// xl.columnIndexMap.forEach((k,v)-> data[]);

		int numberOfRows = xl.getRowCount();
		String[][] data = new String[numberOfRows][];
		String x = "";
		for (int i = 1; i < numberOfRows; i++) {
			for (int j = 0; j < xl.getColumnCount(i); j++) {
				NameValuePair value_pair = xl.getColumnValuePair(j, i);
				x = value_pair.getName() + ":" + value_pair.getValue();
				data[i][j] = x;
			}

		}

		return null;
	}

	@Test(enabled = true)
	public void testExcelLib() throws JSONException {

		ExcelLib_POI xl = new ExcelLib_POI(System.getProperty("user.dir") + "/excel_payload_files/MakePaymentAPI.xlsx","makePaymentRequestParams");
		int numberOfRows = xl.getRowCount();
		int numerOfColumns = xl.getColumnCount(2);

		System.out.println("row count : " + numberOfRows);
		System.out.println("column count : " + numerOfColumns);

		ArrayList<String> columnNamesList = xl.getColumnNamesList();
		// xl.getColumnValuePair(colNum, rowNum)

		Iterator<String> itr = columnNamesList.iterator();

		JSONObject obj = new JSONObject();
		
		while (itr.hasNext()) {
			String colName = itr.next();
			String value = xl.getCellValue(2, colName);
			obj.put(colName, value);
		}
		
		System.out.println(obj);
	}

	
	@Test(enabled = true)
	public void abc(){
		String filePath = System.getProperty("user.dir") + "/excel_payload_files/MakePaymentAPI.xlsx";
		
		File file = new File(filePath);
		
		if(file.exists()){
			System.out.println(System.getProperty("user.dir") + "/excel_payload_files/MakePaymentAPI.xlsx");
		}
		
	}
}
