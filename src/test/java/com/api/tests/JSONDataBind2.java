package com.api.tests;

import org.testng.annotations.Test;

import com.api.beans.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataBind2 {

	@Test
	public void dataBind2() throws  Exception {

		String jsonString = "{\"driverId\": \"abate\",\r\n" + 
				"                   \"url\": \"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\",\r\n" + 
				"                   \"givenName\": \"Carlo\",\r\n" + 
				"                   \"familyName\": \"Abate\",\r\n" + 
				"                   \"dateOfBirth\": \"1932-07-10\",\r\n" + 
				"                   \"nationality\": \"Italian\"}";
		ObjectMapper om = new ObjectMapper();
		Driver per = om.readValue(jsonString, Driver.class);
		System.out.println(jsonString);
		String convertedString = om.writeValueAsString(per);
		System.out.println(convertedString);
		
	}
	
}
