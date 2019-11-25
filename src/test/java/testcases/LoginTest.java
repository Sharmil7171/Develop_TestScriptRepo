package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {

	@Test
	public void loginAsBackManager() throws InterruptedException {

		log.debug("Login Test Begins");
		driver.findElement(By.cssSelector(OR.getProperty("Btn"))).click();
		Thread.sleep(3000);

		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login not successful");
		log.debug("Login successfully executed");
	}
}
