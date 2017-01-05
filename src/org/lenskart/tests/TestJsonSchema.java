package org.lenskart.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class TestJsonSchema {

	@Test(enabled = false)
	public void case1() throws IOException, ProcessingException {
		final JsonNode fstabSchema = Utils.loadResource("/fstab.json");
		final JsonNode good = Utils.loadResource("/fstab-good.json");
		final JsonNode bad = Utils.loadResource("/fstab-bad.json");
		final JsonNode bad2 = Utils.loadResource("/fstab-bad2.json");

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

		final JsonSchema schema = factory.getJsonSchema(fstabSchema);

		ProcessingReport report;

		report = schema.validate(good);
		System.out.println(report);

		report = schema.validate(bad);
		System.out.println(report);

		report = schema.validate(bad2);
		System.out.println(report);
	}

	@Test(enabled = false)
	public void case2() throws IOException, ProcessingException, JSONException {

		File jsonFile = new File("/Users/fcak17801/eclipse/workspace/Payments-API/json_schema_files/paymentMethod_schema.json");
		String jsonData = FileUtils.readFileToString(jsonFile, "UTF-8");
		System.out.println(jsonData);
		
		File jsonSchemaFile = new File("/Users/fcak17801/eclipse/workspace/Payments-API/json_schema_files/getPayments_response.json");
		String jsonSchema = FileUtils.readFileToString(jsonSchemaFile, "UTF-8");
		System.out.println(jsonSchemaFile);
		
		final JsonNode data = JsonLoader.fromString(jsonData);
		final JsonNode schema = JsonLoader.fromString(jsonSchema);

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		JsonValidator validator = factory.getValidator();

		ProcessingReport report = validator.validate(schema, data);
		System.out.println(report.isSuccess());

	}
	
	@Test(enabled = true)
	public void case3() throws IOException, ProcessingException, JSONException {
		
		final JsonNode schemaNode = JsonLoader.fromResource("/Users/fcak17801/eclipse/workspace/Payments-API/json_schema_files/paymentMethod_schema.json");
		final JsonNode respJsonNode = JsonLoader.fromResource("/Users/fcak17801/eclipse/workspace/Payments-API/json_schema_files/getPayments_response.json");
		
		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

		final JsonSchema schema = factory.getJsonSchema(schemaNode);

		ProcessingReport report;

		report = schema.validate(respJsonNode);
		System.out.println(report);
	}
	
}
