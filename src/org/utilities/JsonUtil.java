package org.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	/*
	 * public static JSONObject convert_HttpResponse_to_JsonObject(HttpResponse
	 * httpResponse) throws ParseException, IOException, JSONException {
	 * 
	 * HttpEntity responseEntity = httpResponse.getEntity(); String
	 * responseString = EntityUtils.toString(responseEntity, "UTF-8");
	 * System.out.println("Response String : " + responseString); return new
	 * JSONObject(responseString); }
	 */

	public static JSONObject convert_json_textfile_to_object(String jsonFilePath) throws IOException, JSONException {

		File jsonFile = new File(jsonFilePath);
		String stringContent = FileUtils.readFileToString(jsonFile, "UTF-8");

		System.out.println("Json file content : " + stringContent);
		return new JSONObject(stringContent);
	}

	public static boolean compare_json_file_contents(String sourceJsonPath, String destJsonPath) throws IOException {
		File srcJsonFile = new File(sourceJsonPath);
		File destJsonFile = new File(destJsonPath);
		String sourceContent = FileUtils.readFileToString(srcJsonFile, "UTF-8");
		String destContent = FileUtils.readFileToString(destJsonFile, "UTF-8");

		return sourceContent.equals(destContent);

	}

	public static JSONObject create_flat_json(List<NameValuePair> nameValuePairs) throws JSONException {

		JSONObject jo = new JSONObject();

		for (NameValuePair pair : nameValuePairs) {
			jo.put(pair.getName(), pair.getValue());
		}
		
		return jo;
	}
}
