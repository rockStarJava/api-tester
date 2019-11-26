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

public class APITester2 {
	private static String url;

	@BeforeClass
	public static void before() throws Exception {
		System.out.println("Setting up!");

		url = "http://localhost:8080/sampleapp/api/v1/employees/";
	}

	@Test
	public void getallTest() {
		get(url).then().log().body().assertThat().body("firstName", hasItems("Alex,Lokesh,David"))
				.body("id", hasItems(1, 2, 3)).statusCode(200);
	}

	@Test
	public void getoneTest() {
		given().pathParam("id", 1).when().get("http://localhost:8080/sampleapp/api/v1/employees/{id}").then().log()
				.body().assertThat().body("id", equalTo(1))

				.statusCode(200);

	}

	@Test
	public void postSucessTest() throws JSONException {
		JSONObject sampleJson = new JSONObject();
		sampleJson.put("firstName", "Alex");
		sampleJson.put("lastName", "newton");
		sampleJson.put("email", "anew@gmail.com");

		with().body(sampleJson.toString()).contentType("application/json").when().request("POST", url).then().log()
				.body().assertThat().statusCode(201);

	}

	@Test
	public void postSucess2Test() throws JSONException {
		JSONObject sampleJson = new JSONObject();
		sampleJson.put("firstName", "Sam");
		sampleJson.put("lastName", "Pentram");
		sampleJson.put("email", "sptram@gmail.com");

		with().body(sampleJson.toString()).contentType("application/json").when().request("POST", url).then().log()
				.body().assertThat().statusCode(201);

	}
}
