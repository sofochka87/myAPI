package com.api.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;

public class UserActionTest2 {

	Faker faker = new Faker();

	@BeforeClass
	public void init() {

		RestAssured.baseURI = "https://www.batch8-api-site.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";
	}

	@Test
	public void testPublicUserGetOnlyAdminProfileInfo() {

		given().relaxedHTTPSValidation().
		// .auth().preemptive().basic("admin", "admin").
				when().log().all().get("/users").then()
				// .statusCode(HttpStatus.SC_OK)
				.statusCode(200)
				// .contentType(ContentType.JSON)
				.header("Content-Type", "application/json; charset=UTF-8").body("id", hasSize(1))
				/// get request for
				/// collection will
				/// return array of
				/// objects,so its
				/// id of all
				/// objects
				.body("name", hasItem("admin"))

		;

	}

	@Test
	public void testPublicUserShouldNotBeAble_CreateNewUser() {

		given().relaxedHTTPSValidation().
		// .auth().preemptive().basic("admin", "admin").
				when().log().all()
				.body("{" + "  \"username\" : \"user_b\" ,\n" + "  \"name\" : \"user b\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" + "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" + "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.accept(ContentType.JSON).contentType(ContentType.JSON)
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

		given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin").when().log().all()
				.body("{" + "  \"username\" : \"user_d\" ,\n" + "  \"name\" : \"user c\" ,\n"
						+ "  \"first_name\" : \"super b\", \n" + "  \"last_name\" : \"user last\" ,\n"
						+ "  \"email\" : \"s@aaa.com\" ,\n" + "  \"roles\" : \"author\" ,\n"
						+ "  \"password\" : \"user\" \n" + "}")
				.contentType(ContentType.JSON).post("/users").then()
				// .statusCode(HttpStatus.SC_CREATED)
				.statusCode(201).contentType(ContentType.JSON)
				// .header("Content-Type", "application/json; charset=UTF-8")
				.body("username", is("user_d"))
		// .body("name", hasItem("admin") )
		//
		;

	}

	@Test
	public void testAdminUserShouldBeAbleToUpdateExistingUser() {

		given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin").contentType(ContentType.JSON)
				.when().log().all()
				.body("{\r\n" + "  \"first_name\" : \"user18\", \r\n" + "  \"last_name\" : \"user18\" ,\r\n"
						+ "  \"email\" : \"user18@gmail.com\" ,\r\n" + "  \"roles\" : \"author\" ,\r\n"
						+ "  \"password\" : \"user18\" \r\n" + "}")

				.pathParam("newId", 4).put("/users/{newId}").then()
				// .statusCode(HttpStatus.SC_CREATED)
				.statusCode(200).header("Content-Type", "application/json; charset=UTF-8")
				.body("first_name", is("user18"));

	}

	@Test
	public void testAdminUserShouldBeAbleToDeleteUser() {
		given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin").queryParam("force", true)
				.param("reassign", 1) /// you can also use this for query parameter
				.pathParam("id", 4).when().log().all().delete("/users/{id}")
				// .delete("/users/4")

				.then().statusCode(HttpStatus.SC_OK)
				// .statusCode(200)
				.contentType(ContentType.JSON).body("deleted", is(true)).body("previous.id", equalTo(4));
	}

	@Test
	public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {

		given().relaxedHTTPSValidation().auth().preemptive().basic("user_c", "user").pathParam("id", 2).when().log()
				.all().get("/users/{id}")
				// .delete("/users/4")

				.then().statusCode(HttpStatus.SC_FORBIDDEN)
				// .statusCode(403)
				.contentType(ContentType.JSON).body("message", is("Sorry, you are not allowed to list users."));

		given().relaxedHTTPSValidation().auth().preemptive().basic("user_c", "user").pathParam("id", 1).when().log()
				.all().get("/users/{id}")

				.then().statusCode(HttpStatus.SC_OK)
				// .statusCode(200)
				.contentType(ContentType.JSON).body("id", is(1));

	}
}