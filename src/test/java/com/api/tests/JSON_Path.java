package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.api.utilities.ConfigurationReader;

import cucumber.api.java.en.And;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSON_Path {

	/*
	 * Given Accept type is JSON 
	 * When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/regions
	 * Then status code is 200
	 * And Response content should be JSON 
	 * And 4 regions should be return
	 * And Americas is one of the region name
	 * And 
	 */
	@Ignore
	@Test
	public void testitemsCountFromResponseBody() {
	given().accept(ContentType.JSON)
	.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions")
	.then().assertThat().statusCode(200)
	.and().assertThat().contentType(ContentType.JSON)
	.and().assertThat().body("items.region_id", hasSize(25))
	.and().assertThat().body("items.region_name", hasItem("NOT Murodil's Region")) 
	.and().assertThat().body("items.region_name", 
	          hasItems("NOT Murodil's Region","NOT Murodil's Region","NOT Murodil's Region"));
	}
	
	/*
	 * Given Accept type is JSON
	 * And Params are limit 100
	 * When I sent GET request to 
	 *  http://34.223.219.142:1212/ords/hr/employees
	 * Then status code is 200
	 * And Response content should be JSON 
	 * And 100 employees data should be in JSON response body
	 */
	@Ignore
	@Test
	public void test_with_Query_ParameterAndList() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("items.employee_id", hasSize(100));	
	}
	
	/*
	 * Given Accept type is JSON
	 * And Params are limit 100
	 * When I sent GET request to 
	 *  http://34.223.219.142:1212/ords/hr/employees
	 * Then status code is 200
	 * And Response content should be JSON 
	 * And 100 employees data should be returned:
	 *  "employee_id": 514311,
            "first_name": "Egorriiik",
            "last_name": "Osmanovv",
            "email": "EOSMANOVVV",
	 */
	@Ignore
	@Test
	public void test_with_Path_Parameters() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.and().pathParam("employee_id", 514311)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/{employee_id}")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("employee_id", equalTo(514311),
				           "first_name", equalTo("Egorriiik"),
				           "last_name", equalTo("Osmanovv"),
				           "email", equalTo("EOSMANOVVV"));	
	}
	/*
	 * Given Accept type is JSON
	 * And Parameters are limit 100
	 * When I sent GET request to 
	 *  http://34.223.219.142:1212/ords/hr/employees
	 * Then status code is 200
	 * And Response content should be JSON 
	 * All employee_ids should be returned
	 */
	@Ignore
	@Test 
	public void test_with_JSON_Path() {
		Map<String,Integer> rParamMap = new HashMap<>();
		rParamMap.put("limit", 100);
		Response response = given().accept(ContentType.JSON)  //header
				.and().params(rParamMap)       //query param/request param
				.and().pathParam("employee_id", 514311) // path param
				.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/{employee_id}");
		
		JsonPath json = response.jsonPath();//get JSON body and assign to JSONPath object
		System.out.println(json.getInt("employee_id"));
		System.out.println(json.getString("first_name"));
		System.out.println(json.getString("last_name"));
		System.out.println(json.getString("email"));
		System.out.println(json.getString("job_id"));
		System.out.println(json.getInt("salary"));
		System.out.println(json.getString("links[0].href"));//get specific element from array
		
		//assign all hrefs into a list of Strings
		List<String> hrefs = json.getList("links.href");
		System.out.println(hrefs);
	}
	
	/*
	 * Given Accept type is JSON
	 * And Parameters are limit 100
	 * When I sent GET request to 
	 *  http://34.223.219.142:1212/ords/hr/employees
	 * Then status code is 200
	 * And Response content should be JSON 
	 * All employee data should be returned
	 */
	@Test
	public void test_JSON_Path_with_Lists() {
		Map<String,Integer> rParamMap = new HashMap<>();
		rParamMap.put("limit", 100);
		Response response = given().accept(ContentType.JSON)  //header
				.and().params(rParamMap)       //query param/request param
				.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees");
		assertEquals(response.statusCode(),200); //check status code
		
		JsonPath json = response.jsonPath();
		//JsonPath json = new JsonPath(new File(FilePath.json));
		//JsonPath json = new JsonPath(response.asString());
		
		//get all employee ids into an arrayList
		List<Integer> empIDs = json.getList("items.employee_id");
		System.out.println(empIDs);
		
		//assert that there are 100 employe_ID's
		assertEquals(empIDs.size(),100);
		
		//get all emails and assign into ArrayList
		List<String> emails = json.getList("items.email");
		System.out.println(emails);
		assertEquals(emails.size(), 100);
		
		//get all employee ids that are greater than 150
		List<String> empIDList = json.get("items.findAll{it.employee_id>150}.employee_id");
		System.out.println(empIDList);
		 
		//get all employee last_names, whose salary is more than 7000
		List<String> lastNames = json.get("items.findAll{it.salary>10000}.last_name");
		System.out.println(lastNames);
		
		
	}
}
