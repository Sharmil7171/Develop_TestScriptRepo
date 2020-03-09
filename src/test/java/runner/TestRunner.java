package runner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.Reporter;

import base.Initialization;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
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
