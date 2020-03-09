@addAndDeleteUser
Feature: Automate
     As an Engr. Candidate
     I need to automate http://www.way2automation.com/angularjs-protractor/webtables/
     So I can show my automation capabilities

Background: Browser Navigater
	Given Navigate to the "http://www.way2automation.com/angularjs-protractor/webtables/" web page in browser
	
@addUser
Scenario Outline: Add a user and validate the user has been added to the table
	When I navigate to the Add User overlay by clicking on Add User link on the page
	And I enter <FirstName>, <LastName>, <UserName>, <Password>, <Company>, <Role>, <Email> and <CellPhone> details 
	Then I see the new user row is being added in the table with filled details
	
	Examples:
      |FirstName    |LastName |UserName |Password |Company | Role     |  Email     | CellPhone |
      |Sharmilkumar |Patel    |sp       |121212   |AAA     | Engineer | abc@ab.com | 1111111 |
	
	
@DeleteUser
Scenario: Delete user User Name: novak and validate user has been deleted
	When I see user row with UserName "novak"
	And I delete the user row 
	Then I do not see user row with UserName "novak" in the table
	
	
