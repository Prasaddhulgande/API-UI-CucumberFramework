package com.vtiger.Stepdefinitions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;

import com.vtiger.common.CommonActions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIDefinations extends BaseTest {
   
	public String endpoint;
	public Response resp;
	public CommonActions cm ;
	RequestSpecification request;
	public String strReq;
	
/*	@Before
	public void getScenarioname(Scenario scenario) throws Exception
	{
		initiation();
		vTCName = scenario.getName();
		logger = extent.createTest(vTCName);
		
	}
	
	@After
	public void savereport()
	{
		extent.flush();
	}
	*/

	@Given("endpoint url")
	public void endpoint_url() {
		
		cm = new CommonActions(driver,logger);
		endpoint = prop.getProperty("API_BASE_URI")+apitd.get(vTCName).get("Url");
	}
	@When("user call get method")
	public void user_call_get_method() {
	    resp= RestAssured.get(endpoint);
	    System.out.println(resp.asPrettyString());
	}
	@When("user create the request and call the request method")
	public void user_create_the_request() throws FileNotFoundException {
	    String templatename = System.getProperty("user.dir")+"/src/test/resources/Templates/"+apitd.get(vTCName).get("Template");
	    File f = new File(templatename);
	    StringBuffer sb = new StringBuffer();
	    Scanner scan =new Scanner(f);
	    while(scan.hasNextLine())
	    {
	    	sb.append(scan.nextLine());
	    }
	    String s = sb.toString();
	    String s1 = s.replace("%Name%", apitd.get(vTCName).get("Name"));
	    strReq = s1.replace("%Job%", apitd.get(vTCName).get("Job"));
	    
	    request = RestAssured.given();
	    request.contentType(ContentType.JSON);
	    request.baseUri(endpoint);
	    request.body(strReq);
if(apitd.get(vTCName).get("Method").equalsIgnoreCase("POST"))
{
	    resp = request.post();
}else if(apitd.get(vTCName).get("Method").equalsIgnoreCase("PUT"))
{
	resp = request.put();
}else if(apitd.get(vTCName).get("Method").equalsIgnoreCase("PATCH"))
{
	resp = request.patch();
}
	    System.out.println(resp.asPrettyString());
	}
	@Then("user can see the response")
	public void user_can_see_the_response() {
	    if(!strReq.equals(""))
	    {
		cm.writeAPIInfo(endpoint,strReq, resp.asPrettyString());
	    }else
	    {
		cm.writeAPIInfo(prop.getProperty("API_BASE_URI"), endpoint, resp.asPrettyString());
	    }
	}
	@Then("Status line should be matched")
	public void status_code_should_be() {
	    
		System.out.println("status code="+resp.getStatusCode());
		 cm.writeStatusLine(apitd.get(vTCName).get("StatusLine"), resp.getStatusLine());
		
	}
	
	@Then("validate response data")
	public void validate_response_data() {
	    
		for(int i=1; i<=10; i++)
		{
		if(!apitd.get(vTCName).get("Validation"+i).equals("")) 
		{
			String[] str = apitd.get(vTCName).get("Validation"+i).trim().split("==");
			String jsonpath = str[0].trim();
			String expData = str[1].trim();
			 cm.writeValidation(resp, expData, jsonpath);
		}
		}
		
	}



}
