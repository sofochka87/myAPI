package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.api.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequests {

	/*
	 * given content type is Json
     * And Accept type is Json
     * When I send POST request to 
     * http://34.223.219.142:1212/ords/hr/countries/
     * with request body :
       {
          "region_id": 77771,
          "region_name": "batch8 region"
         }

     * Then status code should be 200
     * And response body should match request body
     */
	@Test
	public void post_New_Region(){
		String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions/";
		//String requestJSON = "{\"region_id\" : 77771,\"region_name\" : \"batch8 region\"}";
		
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 77775);
		requestMap.put("region_name", "batch8 region");
		
		Response response = given().accept(ContentType.JSON)
		                  .and().contentType(ContentType.JSON)
		                  .and().body(requestMap)
		                  .when().post(url);
		System.out.println(response.statusLine());
		response.prettyPrint();
		assertEquals(response.statusCode(), 201);
		
		Map responseMap = response.body().as(Map.class);
		
		//assertEquals(responseMap, requestMap); -> did not work
		assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
		assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));
	}
	
}
