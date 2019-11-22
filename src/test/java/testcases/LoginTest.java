package testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void loginAsBackManager() throws InterruptedException {
		driver.findElement(By.cssSelector(OR.getProperty("Btn"))).click();
		Thread.sleep(3000);
	}
}
