package com.api.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.api.utilities.BrowserUtils;
import com.api.utilities.Driver;

public class HRAppDeptEmpPage {
	private WebDriver driver;
	
	public HRAppDeptEmpPage() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="pt1:ot1")
	public WebElement departmentID;
	
	@FindBy(id="pt1:ot2")
	public WebElement departmentName;
	
	@FindBy(id="pt1:ot3")
	public WebElement managerID;
	
	@FindBy(id="pt1:ot4")
	public WebElement locationID;
	
	@FindBy(id="pt1:cb3")
	public WebElement Next;
	
	@FindBy(id="pt1:r1:0:it1::content")
	public WebElement email;
	
	@FindBy(id="pt1:r1:0:cb1")
	public WebElement FindDetails;
	
	@FindBy(id="pt1:r1:0:ot1")
	public WebElement firstName;
	
	@FindBy(id="pt1:r1:0:ot2")
	public WebElement lastName;
	
	@FindBy(xpath="//div[@id='pt1:pc1:t1::db']//tr")
	public List<WebElement> employeesCount;
	
	@FindBy(id="pt1:pc1:_dchTbr::icon")
	public WebElement detach;
	
	@FindBy(id="pt1:cb6")
	public WebElement query;
	
	public void searchForDepartment(int deptID) {
	    int currentDepId = Integer.parseInt(departmentID.getText());
	    
	    while(currentDepId != deptID) {
	    		Next.click();
	    		BrowserUtils.waitFor(2);
	    		//deptEmpPage = new HRAppDeptEmpPage();
	    		BrowserUtils.waitForVisibility(departmentID, 5);
	    		currentDepId = Integer.parseInt(departmentID.getText());  		
	    }
	}
}









