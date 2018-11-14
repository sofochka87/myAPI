package com.api.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import org.apache.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserActionTest {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://www.vaso-sophie.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";
	}

	@Test
	public void testPublicUserGetOnlyAdminProfileInfo() {
		given().relaxedHTTPSValidation().when().log().all().get("/users").then().statusCode(200)
				// .contentType(ContentType.JSON)
				.header("Content-Type", "application/json; charset=UTF-8").body("id", hasSize(1))
				.body("name", hasItem("sofochka87"));
	}

	@Test(priority=1)
	public void testPublicUserShouldNotBeAble_CreateNewUser() {

		given().relaxedHTTPSValidation().
		// .auth().preemptive().basic("admin", "admin").
				when().log().all()
				.body("{" + "  \"username\" : \"user_b\" ,\n" 
						+ "  \"name\" : \"user b\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" 
						+ "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" 
						+ "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.contentType(ContentType.JSON)
				.post("/users").then()
				// .statusCode(HttpStatus.SC_UNAUTHORIZED)
				.statusCode(401).contentType(ContentType.JSON)
				// .header("Content-Type", "application/json; charset=UTF-8")
				.body("code", is("rest_cannot_create_user"))
		// .body("name", hasItem("admin") )
		//
		;

	}

	@Test
	public void testAdminUserShouldBeAble_CreateNewUser() {

		given().relaxedHTTPSValidation()
		.auth().preemptive()
		.basic("sofochka87", "kakdela123")
		.when().log().all()
				.body("{" + "  \"username\" : \"user_c\" ,\n" 
						+ "  \"name\" : \"user c\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" 
						+ "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" 
						+ "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.contentType(ContentType.JSON).post("/users").then()
				// .statusCode(HttpStatus.SC_CREATED)
				.statusCode(201).contentType(ContentType.JSON)
				// .header("Content-Type", "application/json; charset=UTF-8")
				.body("username", is("user_c"))
		// .body("name", hasItem("admin") )
		//
		;

	}
	@Test(priority=2)
	public void adminUser_ShouldBeAbleto_UpdateExistingUser() {
		given().relaxedHTTPSValidation()
		.auth().preemptive()
		.basic("sofochka87", "kakdela123")
		.when().log().all()
				.body("{" + "  \"username\" : \"King\" ,\n" 
						+ "  \"name\" : \"King\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" 
						+ "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" 
						+ "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.pathParam("newId", 3)
				.put("/users/{newId}")
			.then()
				.statusCode(200)
				.body("first_name", is("sssss"));
	}
	
	@Test
	public void adminUser_ShouldBeAbleto_DeleteExistingUser() {
			given()
				.relaxedHTTPSValidation()
				.auth().preemptive().basic("sofochka87", "kakdela123")
				.queryParam("force", true)
				.param("reassign", 1)
			.when()
				.log().all()
				.delete("/users/13")
			.then()
				.statusCode(HttpStatus.SC_OK)
				//.statusCode(200)
				.contentType(ContentType.JSON)
				.body("deleted", equalTo(true))
				.body("previous.id", equalTo(13))
				;
		}
	
	@Test
	public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {
		
	}	
	




}