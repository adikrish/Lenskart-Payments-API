package org.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class JsonPayloadGenerator {
	
	public static String getPaymentMethods_msite_payload = "{\"customerId\": \"REPLACE_CUSTOMER_ID\",\"storeId\": \"REPLACE_STORE_ID\",\"customerEmail\": \"REPLACE_CUSTOMER_EMAIL\",\"shippingInfo\":{\"pincode\": \"REPLACE_PINCODE\"}}";
	
	public static String aa = "{\r\n   \"customerEmail\":\"REPLACE_CUSTOMER_EMAIL\",\r\n   \"storeId\":\"REPLACE_STORE_ID\",\r\n   \"shippingInfo\":{\r\n      \"pincode\":\"REPLACE_PINCODE\",\r\n      \"country\":\"REPLACE_COUNTRY\"\r\n   },\r\n   \"quote\":{\r\n      \"storeCreditApplied\":\"REPLACE_STORE_CREDIT_APPLIED\",\r\n      \"gvApplied\":\"REPLACE_GV_APPLIED\",\r\n      \"quoteId\":\"265381356\",\r\n      \"grandTotal\":{\r\n         \"price\":49000\r\n      },\r\n      \"items\":[\r\n         {\r\n            \"id\":\"59053628\",\r\n            \"productId\":\"108511\",\r\n            \"sku\":\"sun:vincent-chase-vc-5158-black-grey-gradient-c5-aviator-sunglas\"\r\n         },\r\n         {\r\n            \"id\":\"59053628\",\r\n            \"productId\":\"108511\",\r\n            \"sku\":\"sun:vincent-chase-vc-5158-black-grey-gradient-c5-aviator-sunglas\"\r\n         }\r\n      ]\r\n   }\r\n}\r\n";

	
	//public static List<NameValuePair> apiParams;
	public static List<NameValuePair> generateParams_GetPaymentMethods(String customerIdVal, String storeIdVal, String customerEmailVal, List<NameValuePair> shippingInfoVal){
		 List<NameValuePair> paymentMethodsParams = new ArrayList<NameValuePair>();
		
		 paymentMethodsParams.add(new BasicNameValuePair("customerId", customerIdVal));
		 paymentMethodsParams.add(new BasicNameValuePair("storeId", customerIdVal));
		 paymentMethodsParams.add(new BasicNameValuePair("customerEmail", customerEmailVal));
		 
		 return null;
		
	}
	

}
