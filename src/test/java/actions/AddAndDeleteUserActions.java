package actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Actions;
import base.Initialization;
import locators.PageLocators;

public class AddAndDeleteUserActions {
	PageLocators locators;
	private static AddAndDeleteUserActions instance;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String company;
	private String role;
	private String email;
	private String cellphone;

	public static AddAndDeleteUserActions getInstance() {
		if (instance == null) {
			instance = new AddAndDeleteUserActions();
		}
		return instance;
	}

	public AddAndDeleteUserActions() {
		locators = new PageLocators();
		PageFactory.initElements(Initialization.getInstance().getWebDriver(), locators);
	}

	public void clickOnNewUserLink() {
		Actions.waitUntilElementDisplayed(locators.tableHeader, 60);
		System.out.println("Add User link is present:" + Actions.isElementPresent(locators.addUserLink));
		Actions.clickElement(locators.addUserLink);
	}

	public void addNewUser(String firstname, String lastname, String username, String password, String company,
			String role, String email, String cellphone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.company = company;
		this.role = role;
		this.email = email;
		this.cellphone = cellphone;

		if (Actions.waitUntilElementDisplayed(locators.firstNameInput, 60)) {

			Actions.addTextToInputBox(locators.firstNameInput, firstname);
			Actions.addTextToInputBox(locators.lastNameInput, lastname);
			Actions.addTextToInputBox(locators.userNameInput, username);
			Actions.addTextToInputBox(locators.passwordInput, password);

			if ("AAA".equalsIgnoreCase(company)) {
				Actions.clickElement(locators.radioButtonforCompanyAAA);
			} else if ("BBB".equalsIgnoreCase(company)) {
				Actions.clickElement(locators.radioButtonforCompanyBBB);
			}

			Actions.clickElement(locators.roleInput);
			Select selectRole = new Select(locators.roleInput);
			selectRole.selectByVisibleText("Customer");

			Actions.addTextToInputBox(locators.emailInput, email);
			Actions.addTextToInputBox(locators.cellPhoneInput, cellphone);

			Actions.clickElement(locators.buttonSuccess);
		}
	}

	public void checkUserIsAddedInTable() {
		if (Actions.waitUntilElementDisplayed(locators.addUserLink, 60)) {
			Actions.isTextVisibleOnPage(this.firstname);
			Actions.isTextVisibleOnPage(this.lastname);
			Actions.isTextVisibleOnPage(this.username);
			Actions.isTextVisibleOnPage(this.role);
			Actions.isTextVisibleOnPage(this.email);
		}
	}

	public boolean checkUserRowPresent(String userName) {
		boolean userPresent = Actions.isTextVisibleOnPage(userName);
		System.out.println(userName + " username is Present on the table - " + userPresent);
		return userPresent;
	}

	public void deleteUser(String username) {

		try {
			Actions.clickElement(locators.deleteButton);
			Thread.sleep(2000);
			if (Actions.isTextVisibleOnPage("Confirmation Dialog")) {
				Actions.clickElement(locators.okButton);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
