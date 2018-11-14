Feature: Employee REST Api requests 


Scenario: Post an Employee method test 

	Given Content type and Accept type is JSON 
	When I post a new Employee with "random" id 
	Then Status code is 201 
	And Response JSON should contain Employee info 
	When I send a Get request with "random" id 
	Then Status code is 200 
	And empoyee JSON Response Data should match the posted JSON data 
	
@ApiPost 
Scenario: Post an Employee and Verify in UI 

	Given Content type and Accept type is JSON 
	When I post a new Employee with "random" id 
	Then Status code is 201 
	And Response JSON should contain Employee info 
	And I am on DeptEmpPage 
	And I search for Employee with "random" id 
	Then UI search results must match API post employee data