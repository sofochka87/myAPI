package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.api.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSON_Validation {
	/*
	 * WARM UP TASK: 
	 * GIVEN Content type is JSON 
	 * AND limit is 10 
	 * WHEN I send request to REST API URL 
	 * http://34.223.219.142:1212/ords/hr/regions 
	 * THEN status code should be 200 
	 * THEN I should see following data: 
	 * 1. Europe 
	 * 2. Americans 
	 * 3. Asia
	 * 4. Middle East and Africa
	 */
	@Ignore
	@Test
	public void test_Regions_version_1() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions";
		Response response = given().accept(ContentType.JSON)
				            .and().params("limit", 10)
				            .when().get(url);
		assertEquals(response.getStatusCode(), 200);
        JsonPath json = response.jsonPath();
        
        System.out.println(json.getInt("items[0].region_id"));
        System.out.println(json.getString("items[0].region_name"));
        assertEquals(json.getInt("items[0].region_id"),11111);
        assertEquals(json.getString("items[0].region_name"),"NOT Murodil's Region");
        
        assertEquals(json.getInt("items[1].region_id"),111111);
        assertEquals(json.getString("items[1].region_name"),"NOT Murodil's Region");
        
        assertEquals(json.getInt("items[2].region_id"),101);
        assertEquals(json.getString("items[2].region_name"),"NOT Murodil's Region");
        
        assertEquals(json.getInt("items[3].region_id"),214899);
        assertEquals(json.getString("items[3].region_name"),"NOT Murodil's Region");
	}
	@Ignore
	@Test
	public void test_Regions_version_2() {
			String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions";
			Response response = given().accept(ContentType.JSON).and().params("limit", 10).when().get(url);
			assertEquals(response.getStatusCode(), 200);
	      
	       assertEquals(response.statusCode(),200);
	        
	        //Store into jsonPath > List<map>
	        JsonPath json = response.jsonPath();
	        //DeSerialize json to List<Map>
	        List<Map> regions = json.getList("items", Map.class);
	        Map r = regions.get(0);
	        Map<Integer,String> expectedRegions = new HashMap<>();
	        expectedRegions.put(11111, "NOT Murodil's Region");
	        expectedRegions.put(111111, "NOT Murodil's Region");
	        expectedRegions.put(101, "NOT Murodil's Region");
	        expectedRegions.put(214899, "NOT Murodil's Region");
	        
	        expectedRegions.keySet().forEach(regionId -> {
                    System.out.println("Looking for region_ID: "+ regionId);
                    regions.forEach(map -> {
                    	if(map.get("region_id")==regionId) {
                    		assertEquals(map.get("region_name"), expectedRegions.get(regionId));
                    	}
                    });
               });
	}
	@Ignore
	@Test
	public void test_Regions_version_3() {
			String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions";
			Response response = given().accept(ContentType.JSON).and().params("limit", 10).when().get(url);
			assertEquals(response.getStatusCode(), 200);
	      
	        JsonPath json = response.jsonPath();
	        List<String> testingData = Arrays.asList("11111 NOT Murodil's Region",
	        		                                 "11111 NOT Murodil's Region",
	        		                                 "101 NOT Murodil's Region",
	        		                                 "214899 NOT Murodil's Region");
	        List<String> regionNames = new ArrayList<>();
	        json.getList("items").forEach( item -> {
	        	regionNames.add(((HashMap)item).get("region_id").toString()+" "
	        	+((HashMap)item).get("region_name").toString()); 
	        });
	        assertTrue(regionNames.containsAll(testingData));
	}

	@Test
	public void test_Regions_version_4() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions";
		Response response = given().accept(ContentType.JSON).and().params("limit", 10).when().get(url);
		assertEquals(response.getStatusCode(), 200);
      
       assertEquals(response.statusCode(),200);
        
        //Store into jsonPath > List<map>
        JsonPath json = response.jsonPath();
        //DeSerialize json to List<Map>
        List<Map> regions = json.getList("items", Map.class);
    
        Map<Integer,String> expectedRegions = new HashMap<>();
        expectedRegions.put(11111, "NOT Murodil's Region");
        expectedRegions.put(111111, "NOT Murodil's Region");
        expectedRegions.put(101, "NOT Murodil's Region");
        expectedRegions.put(214899, "NOT Murodil's Region");
        
}
}
