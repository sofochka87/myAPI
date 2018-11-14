package com.api.beans;

public class Driver {

	 String driverId ; 
	  String url ; 
	  String givenName ; 
	  String familyName ; 
	  String dateOfBirth ; 
	  String nationality ;
	  
	  public Driver() {
	  }

	  public Driver(String driverId, String url, String givenName, String familyName, String dateOfBirth,
	      String nationality) {
	    super();
	    this.driverId = driverId;
	    this.url = url;
	    this.givenName = givenName;
	    this.familyName = familyName;
	    this.dateOfBirth = dateOfBirth;
	    this.nationality = nationality;
	  }



	  public String getDriverId() {
	    return driverId;
	  }
	  public void setDriverId(String driverId) {
	    this.driverId = driverId;
	  }
	  public String getUrl() {
	    return url;
	  }
	  public void setUrl(String url) {
	    this.url = url;
	  }
	  public String getGivenName() {
	    return givenName;
	  }
	  public void setGivenName(String givenName) {
	    this.givenName = givenName;
	  }
	  public String getFamilyName() {
	    return familyName;
	  }
	  public void setFamilyName(String familyName) {
	    this.familyName = familyName;
	  }
	  public String getDateOfBirth() {
	    return dateOfBirth;
	  }
	  public void setDateOfBirth(String dateOfBirth) {
	    this.dateOfBirth = dateOfBirth;
	  }
	  public String getNationality() {
	    return nationality;
	  }
	  public void setNationality(String nationality) {
	    this.nationality = nationality;
	  } 
	  
	  
	  @Override
	  public String toString() {
	    return "Driver [driverId=" + driverId + ", url=" + url + ", givenName=" + givenName + ", familyName="
	        + familyName + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + "]";
	  }
	  
	//  {
//	        "driverId": "abate",
//	        "url": "http://en.wikipedia.org/wiki/Carlo_Mario_Abate",
//	        "givenName": "Carlo",
//	        "familyName": "Abate",
//	        "dateOfBirth": "1932-07-10",
//	        "nationality": "Italian"
//	    },

	
}
