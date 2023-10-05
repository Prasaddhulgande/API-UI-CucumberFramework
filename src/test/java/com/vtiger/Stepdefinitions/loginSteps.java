package com.vtiger.Stepdefinitions;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginSteps extends BaseTest {
	
	
	@Before
	public void getScenario(Scenario scenario)
	{
		initiation();
		vTCName = scenario.getName();
		logger=extent.createTest(vTCName);
	}
	@After
	public void savereport()
	{
		extent.flush();
	}
	
	@AfterAll
	public static void tierdown()
	{
		if(driver!=null)
		closeApp();
	}
	
	private static void closeApp() {
		// TODO Auto-generated method stub
		
	}
	@Given("user should be on login page")
	public void user_should_be_on_login_page() {
		if(driver==null)
		launchApp();
	}
	@When("user enters valid credentials and click on login button")
	public void user_enters_valid_credentials_and_click_on_login_button() {
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(td.get(vTCName).get("Userid"), td.get(vTCName).get("Password"));
		
	}
	@Then("user should be navigated to home page")
	public void user_should_be_navigated_to_home_page() {
		HomePage hp = new HomePage(driver, logger);
		hp.verifyHomePage();
	}
	@Then("user can see logout link on home page")
	public void user_can_see_logout_link_on_home_page() {
		HomePage hp = new HomePage(driver, logger);
		hp.verifyHomePage();
	}
	
	@When("user enters invalid credentials and click on login button")
	public void user_enters_invalid_credentials_and_click_on_login_button() {
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(td.get(vTCName).get("Userid"), td.get(vTCName).get("Password"));
	}
	
	@Then("user can see the error message on login page")
	public void user_can_see_error_msg() {
		LoginPage lp = new LoginPage(driver,logger);
		lp.verifyErrorMsg();
	}
	
	@Then("verify application title")
	public void validateTitle() {
		LoginPage lp = new LoginPage(driver,logger);
		lp.verifyTitle(td.get(vTCName).get("ExpTitle"));
	}
	
	@When("user enters userid as {string} and password as {string} click on login button")
	public void user_enters_userid_as_and_password_as_click_on_login_button(String string, String string2) {
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(string, string2);
	}




}
