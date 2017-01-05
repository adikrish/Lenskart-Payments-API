package org.lenskart.tests;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.utilities.ExcelLib_POI;

public class UseCases_MakePayments {

	@Test(enabled = true)
	public void setupInputParams() throws JSONException {

		JSONObject payloadJson = new JSONObject();
		//List<BasicNameValuePair> valuePair = new ArrayList<BasicNameValuePair>();
		ExcelLib_POI xl = new ExcelLib_POI(
				"/Users/fcak17801/eclipse/workspace/Payments-API/excel_payload_files/MakePaymentAPI.xlsx",
				"makePaymentRequestParams");
		int paramCount = xl.getColumnCount(1);

		for(int i = 0; i < paramCount; i++){
			
			NameValuePair colValPair = xl.getColumnValuePair(i, 1);
			payloadJson.put(colValPair.getName(), colValPair.getValue());		
		}
		
		System.out.println(payloadJson);
	}

	
	
	
	
}
