package com.api.tests;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.api.beans.Countries;
import com.api.beans.CountriesResponse;
import com.api.beans.Region;
import com.api.beans.RegionResponse;
import com.api.utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequest2withPojo {
 
	@Ignore
	@Test
	public void post_new_Region_using_POJO() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions/";
		Region region = new Region();
		region.setRegion_id(new Random().nextInt(99999));
		region.setRegion_name("batch8 region");
		
		Response response = given().log().all()
				.accept(ContentType.JSON)
				.and().contentType(ContentType.JSON)
				.and().body(region)
				.when().post(url);
		assertEquals(response.statusCode(),201);
		
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		
		// And response body should match request body
		//region id and region name must match
		assertEquals(responseRegion.getRegion_id(), region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(), region.getRegion_name());
	}
	
	@Test
	public void post_new_Country_using_POJO() {
	String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/countries/";
		
		Countries country = new Countries();
		country.setCountry_id("XP");
		country.setCountry_name("Xxxxxxxxx");
		country.setRegion_id(77771);
		
		Response response = given().log().all()
				.accept(ContentType.JSON)
			   .and().contentType(ContentType.JSON)
			   .and().body(country)
			   .when().post(url);
		
		assertEquals(response.getStatusCode(),201);
		
		CountriesResponse resCountry = response.body().as(CountriesResponse.class);
		
		assertEquals(resCountry.getCountry_id(),country.getCountry_id()); 
		assertEquals(resCountry.getCountry_name(),country.getCountry_name()); 
		assertEquals(resCountry.getRegion_id(),country.getRegion_id());

	}
}
