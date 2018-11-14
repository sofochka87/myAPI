package com.api.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataBind {

	@Test(priority=1)
	public void simpleGetRequest() throws  Exception {

		String jsonString = "{\"name\": \"Adam\",\"age\": \"21\"}";
		ObjectMapper om = new ObjectMapper();
		Person per = om.readValue(jsonString, Person.class);
		System.out.println("Person --> "+per);
		String convertedString =  om.writeValueAsString(per);
		System.out.println("Converted String --> " + convertedString);
	}
	@Test(priority=2)
	public void dataBind3() throws  Exception {
		String jsonStringArr = "[{\"name\": \"Adam\",\"age\": \"21\"},\r\n" + 
				" {\"name\": \"DOG\",\"age\": \"25\"},\r\n" + 
				" {\"name\": \"KOT\",\"age\": \"22\"}]";
		ObjectMapper om = new ObjectMapper();
		Person[] pers = om.readValue(jsonStringArr, Person[].class);
		String convertedString2 =  om.writeValueAsString(pers);
		System.out.println("Converted String --> "+convertedString2);
		System.out.println("Array Person --> "+Arrays.toString(pers));
		
		// converting to an Arraylist instead of Array
		  // we need to use a typeReference object --> Type reference is a abstact class with no abstract method thats why you see body{}
		  List<Person> lst = om.readValue(jsonStringArr, new TypeReference<List<Person>>() {}  ) ;  
		  
		  System.out.println("List ---> " + lst);
		  
		  
	}
}
class Person{
	String name;
	int age;
	public Person() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
