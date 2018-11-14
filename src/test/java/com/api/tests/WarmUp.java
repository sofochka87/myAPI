package com.api.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class WarmUp {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.vaso-sophie.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";
	}
	@Test
	  public void simpleGetRequest() {
	    
	   Response response =  given()
	      .relaxedHTTPSValidation()
	    .when()
	      .log().all()
	      //.queryParam("per_page",2)
	      .get("/posts"); 
	   
	   JsonPath jp = response.jsonPath();
	   
	   List<Object> list = jp.getList("author");
	   
	   List<Integer> lstNum = jp.getList("author", Integer.class);
	   
	   List<String> lstNum2 = jp.getList("author", String.class);
	   
	   List<String> titles = jp.getList("title.rendered", String.class);
	   System.out.println(titles);
	   List<Object> lstCount = jp.getList("_links.version-history.count[0]");
       System.out.println(lstCount);	   
	}
	
}
