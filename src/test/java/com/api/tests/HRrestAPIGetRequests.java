package com.api.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;


import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class HRrestAPIGetRequests {

	@Test
	public void simpleGET() {
		/*
		 * When I send a GET request to REST API URL
		 * http://34.223.219.142:1212/ords/hr/employees
		 * Then response status code should be 200 
		 */
		when().get("http://34.223.219.142:1212/ords/hr/employees")
		.then().statusCode(200);
	}
	    /*
	     * When I send a GET request to REST API URL
		 * http://34.223.219.142:1212/ords/hr/countries
		 * Then I should see JSON response
		 */
	@Ignore
	@Test
	public void printResponse() {
		when().get("http://34.223.219.142:1212/ords/hr/countries")
		.andReturn().body().prettyPrint();		
	}
	/*
	 * When I send a GET request to REST API URL
     * http://34.223.219.142:1212/ords/hr/countries/US
     * And Accept type is "application/json"
     * Then response status code should be 200
     */
	@Ignore
	@Test
	public void getWithHeaders() {
		//accept - application/JSON
		with().accept(ContentType.JSON)
		.when().get("http://34.223.219.142:1212/ords/hr/countries/US")
		.then().statusCode(200);	
	}
	
	/* When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/employees/1234
	 * Then status code is 404
	 * And response body error message is "Not Found"
	 */
	@Ignore
	@Test
	public void negativeGet() {
		//when().get("http://34.223.219.142:1212/ords/hr/employees/asdfs")
		//.then().statusCode(404);
		Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234567");
		assertEquals(response.statusCode(),404);
		assertTrue(response.asString().contains("Not Found"));
		response.prettyPrint();
		
		//response.then().assertThat();
	}
	/*
	 * When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/employees/100
	 * And ACCEPT type is JSON
	 * Then status code is 200
	 * And response content should be JSON */
	@Test
	public void verifyContentTypeWithAssertThat() {
		String url = "http://34.223.219.142:1212/ords/hr/employees/100";
		// WITH, WHEN, GET, ASSERTTHAT, ACCEPT, CONTENTTYPE
		 given().accept(ContentType.JSON)
	    .when().get(url)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON);
	}
	
	/* Given Accept type is JSON 
	 * When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/employees/100
	 * Then status code is 200
	 * And response content should be JSON 
	 * And First Name should be STEVEN */
	@Test
	public void verify_First_Name() throws URISyntaxException {
		URI uri = new URI("http://34.223.219.142:1212/ords/hr/employees/100");
		
		given().accept(ContentType.JSON)
		.when().get(uri)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("first_name",Matchers.equalTo("Steven"))
		.and().assertThat().body("last_name",Matchers.equalTo("King"))
		.and().assertThat().body("employee_id",Matchers.equalTo(100))
		.and().assertThat().body("email", Matchers.equalTo("SKING"));
		
	}
	
}
