package com.api.runners;

import cucumber.api.CucumberOptions;
//import cucumber.api.testng.AbstractTestNGCucumberTests;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", 
				"html:target/cucumber-report",
				"json:target/cucumber.json"
		},
		tags = "@ApiPost",
		features= "src/test/resources/com/api/features",
		glue= "com/api/post_emp",
		dryRun=false 
)
public class CukesRunner{
	

}
