package org.utilities;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestUtil {

	private static HttpClient httpClient = HttpClients.createDefault();

	public static HttpResponse getRequest(String restUrl) throws ClientProtocolException, IOException {
		// httpClient = HttpClients.createDefault();

		HttpGet getReq = new HttpGet(restUrl);
		HttpResponse getResponse = httpClient.execute(getReq);

		return getResponse;
	}

	public static HttpResponse getRequest_with_Jason_ContentType(String restURL)
			throws ClientProtocolException, IOException {
		// httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(restURL);
		request.setHeader("Content-Type", "application/json");
		HttpResponse httpResponse = httpClient.execute(request);
		return httpResponse;
	}

	public static HttpResponse postJSON_Request(String restURL, List<NameValuePair> params) throws Exception {

		HttpPost httpPost = new HttpPost(restURL);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");

		// create JSON object
		JSONObject JsonReqObject = new JSONObject();

		for (NameValuePair nameValuePair : params) {
			try {
				JsonReqObject.put(nameValuePair.getName(), nameValuePair.getValue());
			} catch (JSONException e) {
				System.out.println("Error converting NameValue pair to json object");
			}
		}

		System.out.println("Request Message:" + JsonReqObject.toString());
		// Convert the JSON object to String Entity
		StringEntity JsonEntityObj = new StringEntity(JsonReqObject.toString());
		httpPost.setEntity(JsonEntityObj);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}

	public static HttpResponse postJSON_Request(String restURL, String sessionToken, List<NameValuePair> params)
			throws Exception {

		HttpPost httpPost = new HttpPost(restURL);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("sessiontoken", sessionToken);

		// create JSON object
		JSONObject JsonReqObject = new JSONObject();

		for (NameValuePair nameValuePair : params) {
			try {
				JsonReqObject.put(nameValuePair.getName(), nameValuePair.getValue());
			} catch (JSONException e) {
				System.out.println("Error converting NameValue pair to json object");
			}
		}

		System.out.println("Request Message:" + JsonReqObject.toString());
		// Convert the JSON object to String Entity
		StringEntity JsonEntityObj = new StringEntity(JsonReqObject.toString());
		httpPost.setEntity(JsonEntityObj);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}

	public static HttpResponse postJSON_Request(String restURL, JSONObject jsonObj) throws Exception {

		HttpPost httpPost = new HttpPost(restURL);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		System.out.println("Request Payload:" + jsonObj.toString());

		// Convert the JSON object to String Entity
		StringEntity JsonEntityObj = new StringEntity(jsonObj.toString());
		httpPost.setEntity(JsonEntityObj);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}

	public static HttpResponse postJSON_Request(String restURL, String sessionToken, JSONObject jsonObj)
			throws Exception {

		HttpPost httpPost = new HttpPost(restURL);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("sessiontoken", sessionToken);
		System.out.println("Request Payload:" + jsonObj.toString());

		// Convert the JSON object to String Entity
		StringEntity JsonEntityObj = new StringEntity(jsonObj.toString());
		httpPost.setEntity(JsonEntityObj);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}

	public static JSONObject convert_HttpResponse_to_JsonObject(HttpResponse httpResponse) throws Exception {

		if (httpResponse == null || httpResponse.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Http response is NUll or Response code is not 200!");
		}

		HttpEntity responseEntity = httpResponse.getEntity();
		String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		System.out.println("Response String : " + responseString);
		return new JSONObject(responseString);
	}

}
