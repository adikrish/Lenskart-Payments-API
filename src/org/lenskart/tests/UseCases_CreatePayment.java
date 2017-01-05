package org.lenskart.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lenskart.core.EnvironmentConstants;
import org.lenskart.core.PaymentPathConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.JsonUtil;
import org.utilities.RequestUtil;

public class UseCases_CreatePayment {
	
	private static JSONObject reqJsonObject = null;
	private static JSONObject respJsonObject = null;
	private String restURL= "";
	

	@Test
	public void getPaymentMethods_mobileSite() throws Exception {

		
		restURL = EnvironmentConstants.TEST_ENVIRONMENT + PaymentPathConstants.PAYMENT_METHODS_PATH;
		reqJsonObject = JsonUtil.convert_json_textfile_to_object(
				System.getProperty("user.dir")+ "/API_Payload/getPaymentMethods_mobilesite.json");
		respJsonObject = RequestUtil.convert_HttpResponse_to_JsonObject(RequestUtil.postJSON_Request(restURL, reqJsonObject));
		
		//Compare golden file witht the output
		//JsonUtil.compare_json_file_contents("", "");
		
		//Assert output
		JSONArray payMethodsArray = (JSONArray) respJsonObject.get("paymentMethods");
		Assert.assertNotNull(payMethodsArray, "Payment methods list is NULL");
		Assert.assertTrue(payMethodsArray.length() > 0, "Empty Payments methods list");

	}

}
