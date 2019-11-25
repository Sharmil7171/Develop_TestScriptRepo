package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProvider="getData")
	public void addCustomer() {
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return null;
		
	}
}
