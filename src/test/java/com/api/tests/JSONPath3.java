package com.api.tests;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class JSONPath3 {



	  Faker faker = new Faker();
	  int newUserID ; 
	  
	  @BeforeClass
	  public void init() {
	    
	    RestAssured.baseURI = "http://www.vaso-sophie.dev.cc" ; 
	    RestAssured.basePath = "/wp-json/wp/v2" ; 
	    
	  }
	  @Test
	  public void testJSONPath() {
	    
	    Response response  = 
	    
	      given()
	        .relaxedHTTPSValidation().
	      //.auth().preemptive().basic("admin", "admin").
	      when()
	        .log().all()
	        .get("/users/{id}",1) ;
	    
	    
	    System.out.println( response.asString()  );
	    response.prettyPrint();
	    
	    JsonPath jsonPath = response.jsonPath() ; 
	    
	    System.out.println( jsonPath.getInt("id") );
	    // title , slug , self
	    System.out.println(jsonPath.getString("avatar_urls.24"));
	    System.out.println(jsonPath.getString("slug"));
	    System.out.println(jsonPath.getString("_links.self[0].href"));
//	    "_links": {
//	        "self": [
//	            {
//	                "href": "http://www.vaso-sophie.dev.cc/wp-json/wp/v2/users/1"
//	            }
//	        ]
	  }
	  
	  @Test
	  public void driverInfoTest() {
		  
		    Response response  = 
		      given()
		        .relaxedHTTPSValidation().
		      //.auth().preemptive().basic("admin", "admin").
		      when()
		        .log().all()
		        .get("http://ergast.com/api/f1/drivers.json") ;
		    
		    JsonPath jsonPath = response.jsonPath();
		    String str = jsonPath.setRoot("MRData.DriverTable").get("Drivers[22].givenName");
		    System.out.println(str);	
		    //assertThat(str, equalToIgnoringCase("Mario"));
	  }
	  
}
