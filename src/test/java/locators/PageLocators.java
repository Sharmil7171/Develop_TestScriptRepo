package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageLocators {
	//tr[@class='smart-table-header-row']
	
	@FindBy(how = How.XPATH, using = "//tr[@class='smart-table-header-row']")
	public WebElement tableHeader;
	
	@FindBy(how = How.XPATH, using = "//thead/tr[2]/td/button")
	public WebElement addUserLink;
	
	@FindBy(how = How.XPATH, using = "//input[@name='FirstName']")
	public WebElement firstNameInput;
	
	@FindBy(how = How.XPATH, using = "//input[@name='LastName']")
	public WebElement lastNameInput;
	
	@FindBy(how = How.XPATH, using = "//input[@name='UserName']")
	public WebElement userNameInput;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Password']")
	public WebElement passwordInput;
	
	@FindBy(how = How.XPATH, using = "//label[1]/input")
	public WebElement radioButtonforCompanyAAA;
	
	@FindBy(how = How.XPATH, using = "//label[2]/input")
	public WebElement radioButtonforCompanyBBB;
	
	@FindBy(how = How.XPATH, using = "//select[@name='RoleId']")
	public WebElement roleInput;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Email']")
	public WebElement emailInput;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Mobilephone']")
	public WebElement cellPhoneInput;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success']")
	public WebElement buttonSuccess;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[3]/td[11]/button")
	public WebElement deleteButton;
	
	@FindBy(how = How.XPATH, using = "//button[2]")
	public WebElement okButton;
	
}
