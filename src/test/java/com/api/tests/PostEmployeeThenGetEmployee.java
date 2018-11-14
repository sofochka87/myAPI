 package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;
import com.api.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostEmployeeThenGetEmployee {

		/*
		Given Content type and Accept type is Json
		When I post a new Employee with "1100" id
		Then Status code is 201
		And Response Json should contain Employee info
		When i send a GET request with "1100" id 
		Then status code is 200
		And employee JSON Response Data should match the posted JSON data
		*/
		
		/*
		 * AD_PRES
			AD_VP
			AD_ASST
			FI_MGR
			FI_ACCOUNT
			AC_MGR
			AC_ACCOUNT
			SA_MAN
			SA_REP
			PU_MAN
			PU_CLERK
			ST_MAN
			ST_CLERK
			SH_CLERK
			IT_PROG
			MK_MAN
			MK_REP
			HR_REP
			PR_REP
		 */
		
		@Test
		public void PostEmployeeThenGetEmployee() {
			int randomID = new Random().nextInt(999999);
			String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/";

			Map reqEmployee = new HashMap();
			reqEmployee.put("employee_id", randomID);
			reqEmployee.put("first_name","Human");
			reqEmployee.put("last_name","Humanoid");
			reqEmployee.put("email","EM"+randomID);
			reqEmployee.put("phone_number", "333.333.3333");
			reqEmployee.put("hire_date","2018-04-24T07:25:00Z");
			reqEmployee.put("job_id","IT_PROG");
			reqEmployee.put("salary",7000);
			reqEmployee.put("commission_pct",null);
			reqEmployee.put("manager_id",null);
			reqEmployee.put("department_id",90);
			
			//Given Content type and ACCEPT type is JSON
			Response response = given().accept(ContentType.JSON)
								.and().contentType(ContentType.JSON)
								.and().body(reqEmployee)
								.when().post(url);
			
			assertEquals(response.statusCode(),201);
			
			Map postResEmployee = response.body().as(Map.class);
			//	And Response JSON should contain Employee info
			reqEmployee.keySet().forEach((Object key) -> {
				System.out.println(postResEmployee.get(key)+" <> "+reqEmployee.get(key));
				assertEquals(postResEmployee.get(key),reqEmployee.get(key));
			});
			/*When i send a GET request with "1100" id 
			Then status code is 200
			And employee JSON Response Data should match the posted JSON data
			*/
			
			response = given().accept(ContentType.JSON)
					   .when().get(url+randomID);
			
			assertEquals(response.statusCode(),200);
			
			Map getResMap = response.body().as(Map.class);
			
			reqEmployee.keySet().forEach(key -> {
				System.out.println(key + ": " +reqEmployee.get(key) + "<>" +getResMap.get(key));
				assertEquals(getResMap.get(key),reqEmployee.get(key));
			});
			
		}
}
