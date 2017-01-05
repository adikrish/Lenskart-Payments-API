package org.lenskart.tests;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utilities.ExcelLib_POI;

public class UseCases_GetPaymentMethods {

	// Create URL with server and end points.
	// Make call with input from json file
	// Compare the output with Expected json result file OR assert for certain
	// values OR make mongo connection and compare

	// Flatten response JSON using util method and compare the content.
	ExcelLib_POI xl = null;//new ExcelLib_POI("","");
	int numberOfRows = 0;

	@BeforeClass
	public void setupExcel() {

		xl = new ExcelLib_POI(System.getProperty("user.dir") + "/excel_payload_files/MakePaymentAPI.xlsx", "makePaymentRequestParams");
		numberOfRows = xl.getRowCount();

		System.out.println("row count : " + numberOfRows);

	}

	public JSONObject getTestData(int rowNum) throws JSONException {
		
		int numberOfRows = xl.getRowCount();
		int numerOfColumns = xl.getColumnCount(2);

		System.out.println("row count : " + numberOfRows);
		System.out.println("column count : " + numerOfColumns);

		ArrayList<String> columnNamesList = xl.getColumnNamesList();
		// xl.getColumnValuePair(colNum, rowNum)

		Iterator<String> itr = columnNamesList.iterator();

		JSONObject jsonObj = new JSONObject();

		while (itr.hasNext()) {
			String colName = itr.next();
			String value = xl.getCellValue(rowNum, colName);
			jsonObj.put(colName, value);
		}
		
		return jsonObj;

	}

	@Test(enabled = true)
	public void testcase01() throws JSONException {

		JSONObject jsonObj =  getTestData(2);   // this is the row number of the test data in excel sheet.
		System.out.println(jsonObj);
		//Make the post call with jsonObj here....
		
		
	}
}
