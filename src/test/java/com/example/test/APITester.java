package com.example.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITester {

	private static String url;

	@BeforeClass
	public static void before() throws Exception {
		System.out.println("Setting up!");

		url = "http://localhost:8080/sampleapp/api/v1/employees/";
	}

	@Test
	public void validateGetWithPathparams() {
		String pathparams = "id";
		String bodyPath = "id";
		given().pathParam(pathparams, 1).when().get(url + "{" + pathparams + "}").then().log().body().assertThat()
				.statusCode(200).body(bodyPath, equalTo(1));
	}

	@Test
	public void validateGetall() {
		String bodyPath = "id";
		get(url).then().log().body().assertThat().statusCode(200).body(bodyPath, hasItems(1, 2, 3));
	}

	@Test
	public void whenValidateResponseTime_thenSuccess() {
		when().get(url).then().time(lessThan(5000L));
	}

	@Test
	public void validatePost() throws JSONException {
		
		JSONObject sampleJson = new JSONObject();
		sampleJson.put("firstName", "john");
		sampleJson.put("lastName", "Doe");
		sampleJson.put("email", "anew@gmail.com");
		with().body(sampleJson.toString()).contentType("application/json").when().request("POST", url).then().log()
				.body().assertThat().statusCode(201);

	}

}
