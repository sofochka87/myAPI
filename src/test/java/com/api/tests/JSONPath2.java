package com.api.tests;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class JSONPath2 {
	
	Faker faker = new Faker(); 
	int newUserID;

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.vaso-sophie.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";
		
		newUserID = create_randomUser();
	}

	public int create_randomUser() {
	    
	    // JSONPATH IS A Library to work with JSON DATA 
	      Response result  = 
	          given()
	            .relaxedHTTPSValidation()
	            .auth().preemptive().basic("admin", "admin").
	          when()
	            .log().all()
	            .accept(ContentType.JSON)
	            .contentType(ContentType.JSON)
	            .body("{" +
	                "  \"username\" : \""+  faker.name().firstName()+"\",\n" + 
	                "  \"name\" : \"user c\" ,\n" + 
	                "  \"first_name\" : \"super b\", \n" + 
	                "  \"last_name\" : \"user last\" ,\n" + 
	                "  \"email\" : \""+  faker.internet().emailAddress()+ "\" ,\n" + 
	                "  \"roles\" : \"author\" ,\n" + 
	                "  \"password\" : \"user\" \n" + 
	                "}")
	            
	            .post("/users") ; 
	      
	      int newID = result.jsonPath().getInt("id") ; 
	      System.out.println( "new ID is " + newID );  
	        
	        //.statusCode(HttpStatus.SC_CREATED)
	         
	          return newID;
	  }
	  
}
