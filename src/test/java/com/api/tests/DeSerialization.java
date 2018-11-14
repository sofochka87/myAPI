package com.api.tests;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.api.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class DeSerialization {

	@Ignore
	@Test
	public void test_with_JSON_to_HashMap() {
		Response response = given().accept(ContentType.JSON)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/514311");
		Map<String,String> map = response.as(HashMap.class);
		System.out.println(map.keySet());
		System.out.println(map.values());
		
		assertEquals(map.get("employee_id"),514311);
		assertEquals(map.get("job_id"),"IT_PROG");
	}
	
	@Test
	public void convert_JSON_to_ListOfMap() {
		Response response = given().accept(ContentType.JSON)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/departments");
		
		/*
		 * Convert the response that contains department information into
		* list of Map*/
		//List<Map<String,String>>
		
		List<Map> listOfMap = response.jsonPath().getList("items",Map.class);
		System.out.println(listOfMap.get(0));
		
		//assert that first department name is "IT department"
		assertEquals(listOfMap.get(0).get("department_name"), "IT department");
		assertEquals(listOfMap.get(0).get("department_id"), 539);
}
}
