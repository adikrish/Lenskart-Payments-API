package org.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFlattener {
	public static Map<String, String> finalFlatJson;
	public static int iCounter = 0;
	public static int jCounter = 0;
	public static int count = 0;

	public Map<String, String> parse(JSONObject jsonObject) throws JSONException {
		Map<String, String> flatJson = new HashMap<String, String>();
		finalFlatJson = new HashMap<String, String>();
		flatten(jsonObject, flatJson, "");
		return flatJson;
	}

	public List<Map<String, String>> parse(JSONArray jsonArray) throws JSONException {
		List<Map<String, String>> flatJson = new ArrayList<Map<String, String>>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Map<String, String> stringMap = parse(jsonObject);
			flatJson.add(stringMap);
		}
		return flatJson;
	}

	public List<Map<String, String>> parseJson(String json) throws Exception {
		List<Map<String, String>> flatJson = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			flatJson = new ArrayList<Map<String, String>>();
			flatJson.add(parse(jsonObject));
		} catch (JSONException je) {
			flatJson = handleAsArray(json);
		}
		return flatJson;
	}

	private List<Map<String, String>> handleAsArray(String json) throws Exception {
		List<Map<String, String>> flatJson = null;
		try {
			JSONArray jsonArray = new JSONArray(json);
			flatJson = parse(jsonArray);
		} catch (Exception e) {
			throw new Exception("Json might be malformed");
		}
		return flatJson;
	}

	private void flatten(JSONArray obj, Map<String, String> flatJson, String prefix) throws JSONException {
		int length = obj.length();
		for (int i = 0; i < length; i++) {
			if (obj.get(i).getClass() == JSONArray.class) {
				JSONArray jsonArray = (JSONArray) obj.get(i);
				if (jsonArray.length() < 1)
					continue;
				flatten(jsonArray, flatJson, prefix + i);
			} else if (obj.get(i).getClass() == JSONObject.class) {
				JSONObject jsonObject = (JSONObject) obj.get(i);
				flatten(jsonObject, flatJson, prefix + (i + 1));
			} else {
				String value = obj.getString(i);
				if (value != null)
					finalFlatJson.put(prefix + (i + 1), value);
				flatJson.put(prefix + (i + 1), value);
			}
		}
	}

	private void flatten(JSONObject obj, Map<String, String> flatJson, String prefix) throws JSONException {
		Iterator iterator = obj.keys();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (obj.get(key).getClass() == JSONObject.class) {
				JSONObject jsonObject = (JSONObject) obj.get(key);
				flatten(jsonObject, flatJson, prefix);
			} else if (obj.get(key).getClass() == JSONArray.class) {
				JSONArray jsonArray = (JSONArray) obj.get(key);
				if (jsonArray.length() < 1)
					continue;
				flatten(jsonArray, flatJson, prefix + key);
			} else {
				String value = obj.getString(key);
				if (value != null && !value.equals("null"))
					iCounter++;
				count++;
				finalFlatJson.put(prefix + key, value);
				System.out.println("Key: " + prefix + key + " Value:" + obj.getString(key));
				flatJson.put(prefix + key, value);
			}
		}
	}
}
