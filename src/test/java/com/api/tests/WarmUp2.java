package com.api.tests;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.beans.Driver;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WarmUp2 {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "http://ergast.com/api/f1/drivers.json";
		RestAssured.useRelaxedHTTPSValidation();
	}

	@Test
	public void simpleGetRequest() {

		Response res = given().when().get("http://ergast.com/api/f1/drivers.json");

		JsonPath jp = res.jsonPath();
		List<String> lstGivenName = jp.getList("MRData.DriverTable.Drivers.givenName", String.class);

		System.out.println(lstGivenName);

		// System.out.println(jp.getList("MRData.DriverTable.Drivers[0]"));

		Map map1 = jp.getMap("MRData.DriverTable.Drivers[0]");
		System.out.println(map1);
		System.out.println(map1.keySet());
		Map<String, String> map2 = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);
		System.out.println(map2.values().contains("George"));

		// JSONPATH that rest assured use is the GPath from Groovy
		// Predicate
		List<String> george = jp.get("MRData.DriverTable.Drivers.findAll{it.givenName=='George'}");
		System.out.println(george);
		// System.out.println(jp.get("MRData.DriverTable.Drivers.findAll{
		// it.givenName=='George'}") );
		// System.out.print(
		// jp.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George' &&
		// it.nationality=='American' }") );
		System.out.println();
		Predicate<String> predicate = name -> name.equalsIgnoreCase("George");
		lstGivenName.forEach(name -> {
			if (predicate.test(name)) {
				System.out.println("Georges: " + name);
			}
		});

		List<Object> lst3 = jp.get("MRData.DriverTable.Drivers.findAll{it.givenName.length()==3}");
		List<Object> lst4 = jp
				.get("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName.length()==3}.familyName");
		System.out.println(lst4);
//		List<Object> lst5 = jp.get(
//				jp.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George' && it.nationality=='American' }"));
//		System.out.println(lst5);
		
		// single JSON object --> Driver
		// find out all the driver that has 3 letters given name it.givenName.length()

		// single json object ---> Driver object in java
		//Data binding -> Binding JSON field to POJO field
		Driver driverObj = jp.getObject("MRData.DriverTable.Drivers[0]", Driver.class);
		System.out.println(driverObj);

	}
}
