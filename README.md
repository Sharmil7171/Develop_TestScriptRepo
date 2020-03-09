# Web Automation Framework: 

| Command | Description |
| --- | --- |
| Test Framework | TestNg |
| Web Driver | Selenium |
| BDD | Cucumber |
| Reporting | Extend Reports |


## Add the Feature file
```
@addAndDeleteUser
Feature: Automate
     As an Engr. Candidate
     I need to automate http://www.way2automation.com/angularjs-protractor/webtables/
     So I can show my automation capabilities

Background: Browser Navigater
	Given Navigate to the "http://abc.com/webtables/" web page in browser
	
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
```

## Create the Runner
```
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, 
				 features = "src/test/resources/FeatureFiles/AddAndDeleteUser.feature", 
				 glue = {"stepDefinition" }, 
				 tags = { "@addAndDeleteUser" })
public class TestRunner extends AbstractTestNGCucumberTests {
	static Initialization ini = Initialization.getInstance();

	@BeforeClass
	public static void driverInitilization() {
		ini.initializeDrivers("chrome");
	}

	@AfterClass
	public static void tearDown() {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + File.separator + "extent-config.xml"));
		ini.tearDownDriver();
	}

}
```

## Use the testng.xml file to run the test cases
```
<suite name="Suite">
	<test name="Test">
		<classes>
			<class name="runner.TestRunner" />
		</classes>
	</test>
</suite>
```
