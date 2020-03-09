package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.AddAndDeleteUserActions;
import base.Initialization;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddAndRemoveUser {

	Initialization driverInit = Initialization.getInstance();
	WebDriver driver = driverInit.getWebDriver();
	AddAndDeleteUserActions addDeleteActions = AddAndDeleteUserActions.getInstance();
	private String deleteUser;

	@Given("^Navigate to the \"([^\"]*)\" web page in browser$")
	public void navigate_to_the_something_web_page_in_browser(String webURL) {
		driverInit.openWebPage(webURL);
	}

	@When("^I navigate to the Add User overlay by clicking on Add User link on the page$")
	public void i_navigate_to_the_add_user_overlay_by_clicking_on_add_user_link_on_the_page() {
		addDeleteActions.clickOnNewUserLink();
	}

	@When("^I see user row with UserName \"([^\"]*)\"$")
	public void i_see_user_row_with_username_something(String username) {
		boolean userpresent = addDeleteActions.checkUserRowPresent(username);
		System.out.println(
				username + " username row visible on the page - " + userpresent);
		this.deleteUser = username;
		Assert.assertEquals(true, userpresent);
	}

	@Then("^I see the new user row is being added in the table with filled details$")
	public void i_see_the_new_user_row_is_being_added_in_the_table_with_filled_details() {
		addDeleteActions.checkUserIsAddedInTable();
	}

	@Then("^I do not see user row with UserName \"([^\"]*)\" in the table$")
	public void i_do_not_see_user_row_with_username_something_in_the_table(String username) {
		boolean userpresent = addDeleteActions.checkUserRowPresent(username);
		System.out.println(
				username + " username row not visible on the page after delete - " + userpresent);
		Assert.assertEquals(false, userpresent);

	}

	@And("^I enter (.+), (.+), (.+), (.+), (.+), (.+), (.+) and (.+) details$")
	public void i_enter_and_details(String firstname, String lastname, String username, String password, String company,
			String role, String email, String cellphone) {
		addDeleteActions.addNewUser(firstname, lastname, username, password, company, role, email, cellphone);
	}

	@And("^I delete the user row$")
	public void i_delete_the_user_row() {
		addDeleteActions.deleteUser(this.deleteUser);
	}
}
