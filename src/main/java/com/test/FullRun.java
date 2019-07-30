package com.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.test.xrayapis.TestExecution;
import com.test.xrayapis.TestRun;
import com.test.xrayapis.XrayAPIIntegration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;  

public class FullRun {
	// GET HTTP Protocol which is used to request data from a specific resource

	// POST methods---is used to send data to a server to create the resource.
		  private static final Logger LOGGER = LogManager.getLogger(FullRun.class);

	String testExecutionid;
	XrayAPIIntegration apiIntegration = new XrayAPIIntegration();
	XrayReport report = new XrayReport();

	@Test(priority = 0)
	public void createIssue() throws URISyntaxException {
		String issueType = "Test Execution";
		testExecutionid=apiIntegration.createIssue(issueType);
		 Assert.assertNotNull(testExecutionid);
		 
	}
	@Test(priority = 1)
	public void postTestExecution() throws URISyntaxException {
		int status;
		status=apiIntegration.postTestExecution(testExecutionid);
		assertEquals(200, status);
	}
	
	@Test(priority = 2)
	public void createEmployee() throws URISyntaxException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		String requestBody = "{\n" + "  \"name\": \"ABC\",\n" + "  \"salary\": \"5000\",\n" + "  \"age\": \"20\"\n"
				+ "}";

		Response response = null;

		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/create");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Response :" + response.asString());
		LOGGER.info("Status Code :" + response.getStatusCode());
		LOGGER.info("Does Reponse contains 'ABC'? :" + response.asString().contains("ABC"));
/*
		System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println("Does Reponse contains 'ABC'? :" + response.asString().contains("ABC"));*/

		TestRun testRun = apiIntegration.getTestRun("TP-2",testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");

		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL")){
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");
			apiIntegration.createIssueBug();
		}

		assertEquals(200, response.getStatusCode());

	}

	// An update operation will happen if the Request-URI;PUT is idempotent
	// means if you try to make a request multiple times,
	// it would result in the same output as it would have no effect.
	@Test(priority = 3)
	public void updateEmployee() throws URISyntaxException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		String requestBody = "{\r\n" + " \"name\":\"put_test_employee\",\r\n" + " \"salary\":\"1123\",\r\n"
				+ " \"age\":\"23\"\r\n" + "}";

		Response response = null;

		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).put("/update/4710");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Does Reponse contains 'ABC'? :" + response.asString().contains("ABC"));
		LOGGER.info("Response :" + response.asString());
		LOGGER.info("Status Code :" + response.getStatusCode());
		LOGGER.info("Does Reponse contains 'put_test_employee'? :" + response.asString().contains("put_test_employee"));
		
		/*
		System.out.println("Does Reponse contains 'ABC'? :" + response.asString().contains("ABC"));
		System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println(
				"Does Reponse contains 'put_test_employee'? :" + response.asString().contains("put_test_employee"));
		System.out.println("Does Reponse contains 'ABC'? :" + response.asString().contains("ABC"));*/

		TestRun testRun = apiIntegration.getTestRun("TP-3",testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");
		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL")){
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");
			apiIntegration.createIssueBug();
		}
		assertEquals(200, response.getStatusCode());
	}

	// it is used to �Delete� any resource specified
	@Test(priority = 4)
	public void deleteEmployee() throws URISyntaxException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		Response response = null;

		try {
			response = RestAssured.given().contentType(ContentType.JSON).delete("/delete/11400");
		} catch (Exception e) {
			e.printStackTrace();
		}
		TestRun testRun = apiIntegration.getTestRun("TP-4",testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");
		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL")){
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");
			apiIntegration.createIssueBug();

		}
		LOGGER.info("Response :" + response.asString());
		LOGGER.info("Status Code :" + response.getStatusCode());
		LOGGER.info("Does Reponse contains 'put_test_employee'? :" + response.asString().contains("put_test_employee"));

		
		/*System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println(
				"Does Reponse contains 'put_test_employee'? :" + response.asString().contains("put_test_employee"));
*/
	}

	// POJO (Plain Old Java Object) and we need to send it to the API call
	// @Test
	public void testSerialization() {

		Response response = null;

		Student student = new Student();
		student.setAge(10);
		student.setWeight(100);
		student.setHome("India");

		response = RestAssured.given().contentType("application/json").body(student).when()
				.post("http://www.thomas-bayer.com/restnames/countries.groovy");
		
		LOGGER.info("Response :" + response.asString());
		LOGGER.info("Status Code :" + response.getStatusCode());
		LOGGER.info("Does Reponse contains 'Country-Name'? :" + response.asString().contains("Belgium"));

		
		/*System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println("Does Reponse contains 'Country-Name'? :" + response.asString().contains("Belgium"));*/
		assertEquals(200, response.getStatusCode());

	}

	// we have the API response and you would need to de-serialize it into a
	// POJO
	// @Test
	public void testDeSerialization() {

		Student student = RestAssured.get("http://www.thomas-bayer.com/restnames/countries.groovy").as(Student.class);

		LOGGER.info(student.toString());
		LOGGER.info("student :" + student.toString());
		LOGGER.info("Does Reponse contains 'Country-Name'? :" + student.toString().contains("Belgium"));

		
		/*System.out.println(student.toString());
		System.out.println("student :" + student.toString());
		System.out.println("Does Reponse contains 'Country-Name'? :" + student.toString().contains("Belgium"));*/
	}

	@Test(priority = 5)
	public void mailsend() {
		mail test1 = new mail();
		test1.mailm("test-output//emailable-report.html");

	}

	@AfterSuite
	public void afterAllTest() {
		List<TestExecution> testExecution = apiIntegration.getTestExecution(testExecutionid);

		try {
			report.sendReportAsExcel(testExecution,testExecutionid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}