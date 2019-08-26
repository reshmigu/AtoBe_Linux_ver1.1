package com.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.test.xrayapis.CreateIssueDTO;
import com.test.xrayapis.GenerateJasperReport;
import com.test.xrayapis.JasperBugDTO;
import com.test.xrayapis.JasperReportDTO;
import com.test.xrayapis.ResponseDTO;
import com.test.xrayapis.TestExecution;
import com.test.xrayapis.TestRun;
import com.test.xrayapis.XrayAPIIntegration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.sf.jasperreports.engine.JRException;

public class Run {
	// GET HTTP Protocol which is used to request data from a specific resource

	// POST methods---is used to send data to a server to create the resource.
	private static final Logger LOGGER = LogManager.getLogger(FullRun.class);

	String testExecutionid;
	XrayAPIIntegration apiIntegration = new XrayAPIIntegration();
	XrayReport report = new XrayReport();
	CreateIssueDTO createIssueDTO = null;
	CreateIssueDTO createBugDTO = null;
	private static final ResourceBundle rb = ResourceBundle.getBundle("application");
	private static final String BASE_URL = rb.getString("baseUrl");
	private static final String project_Name = rb.getString("project.name");
	private static final String xray_link = rb.getString("xray.link");
	private static final String project_id = rb.getString("project.id");

	@Test(priority = 0)
	public void createIssue() throws URISyntaxException {
		createIssueDTO = new CreateIssueDTO();
		LocalDate date=LocalDate.now();
		createIssueDTO.setDescription("AtoBe Automated Test Run "+ date.toString());
		createIssueDTO.setKey("TP");
		createIssueDTO.setName("Test Execution");
		createIssueDTO.setSummary("AtoBe Test Run "+ date.toString());
		testExecutionid = apiIntegration.createIssue(createIssueDTO);
		Assert.assertNotNull(testExecutionid);
	}

	@Test(priority = 1)
	public void postTestExecution() throws URISyntaxException {
		int status;
		status = apiIntegration.postTestExecution(testExecutionid);
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

		TestRun testRun = apiIntegration.getTestRun("TP-2", testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");

		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");

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
		TestRun testRun = apiIntegration.getTestRun("TP-3", testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");
		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");
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
		TestRun testRun = apiIntegration.getTestRun("TP-4", testExecutionid);
		if (response.getStatusCode() == 200 && !testRun.getStatus().equals("PASS"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "PASS");
		else if (response.getStatusCode() != 200 && !testRun.getStatus().equals("FAIL"))
			apiIntegration.updateTestCaseStatus(testRun.getId(), "FAIL");
		LOGGER.info("Response :" + response.asString());
		LOGGER.info("Status Code :" + response.getStatusCode());
		LOGGER.info("Does Reponse contains 'put_test_employee'? :" + response.asString().contains("put_test_employee"));

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

	}

/*	@Test(priority = 5)
	public void mailsend() {
		mail test1 = new mail();
		test1.mailm("test-output//emailable-report.html");

	}*/

	@AfterSuite
	public void afterAllTest() throws URISyntaxException, JRException {
		List<TestExecution> testExecution = apiIntegration.getTestExecution(testExecutionid);
		
		List<JasperBugDTO> jasperBugDTOList=new ArrayList<>();
		testExecution.forEach(a->{
			JasperBugDTO jasperBugDTO=new JasperBugDTO();
			
			jasperBugDTO.setTestStatus(a.getStatus());
			jasperBugDTO.setTestCaseId(a.getKey());
			jasperBugDTO.setTestCaseLink(BASE_URL+"/browse/"+a.getKey());
			jasperBugDTOList.add(jasperBugDTO);
		});
		try {
			TestRun response=apiIntegration.getTestRun(testExecution.get(0).getKey(), testExecutionid);
			GenerateJasperReport generateJasperReport= new GenerateJasperReport();
		//	report.sendReportAsExcel(testExecution, testExecutionid);
			JasperReportDTO jasperReportDTO = new JasperReportDTO();
			jasperReportDTO.setProjectName(project_Name);
			jasperReportDTO.setIssueId(testExecutionid);
			jasperReportDTO.setDescription(createIssueDTO.getDescription());
			jasperReportDTO.setSummary(createIssueDTO.getSummary());
			jasperReportDTO.setStartedDate(response.getStartedOn());
			jasperReportDTO.setEndDate(response.getFinishedOn());
			jasperReportDTO.setJasperBugDTO(jasperBugDTOList);
			jasperReportDTO.setAssignee("assignee");
			jasperReportDTO.setExecutedBy(response.getExecutedBy());
			jasperReportDTO.setIssueIdLink(BASE_URL+"/browse/"+testExecutionid);
			jasperReportDTO.setXrayLink((BASE_URL+xray_link).replace("selectedProjectKey=", "selectedProjectKey="+project_id));
			generateJasperReport.createReport(jasperReportDTO,jasperBugDTOList);
			
			mail test1 = new mail();
			VelocityContext context = new VelocityContext();
			// Parameters for report
			int totalTestCases = 0;
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			if (jasperBugDTOList != null && !jasperBugDTOList.isEmpty()) {
				totalTestCases = jasperBugDTOList.size();
			}
			int passCount=(int)jasperBugDTOList.stream().filter(a->a.getTestStatus().equalsIgnoreCase("PASS")).count();
			int failCount=(int)jasperBugDTOList.stream().filter(a->a.getTestStatus().equalsIgnoreCase("FAIL")).count();
			int bugCount=(int)jasperBugDTOList.stream().filter(a->a.getBugLink()!=null).count();
			context.put("projectName", jasperReportDTO.getProjectName());
			context.put("issueId", jasperReportDTO.getIssueId());
			context.put("summary", jasperReportDTO.getSummary());
			context.put("description", jasperReportDTO.getDescription());
			context.put("startedDate", jasperReportDTO.getStartedDate());
			context.put("endDate", jasperReportDTO.getEndDate());
			context.put("executedBy", jasperReportDTO.getExecutedBy());
			context.put("assignee", jasperReportDTO.getAssignee());
			context.put("totalTestCases", totalTestCases);
			context.put("passCount", passCount);
			context.put("failCount", failCount);
			context.put("bugCount", bugCount);
			context.put("xrayLink", jasperReportDTO.getXrayLink());
			context.put("issueLink", jasperReportDTO.getIssueIdLink());
			context.put("jasperBugDTOList", jasperBugDTOList);
			
			test1.sendEmailWithTemplate("test", Arrays.asList("nasia.t@thinkpalm.com"), "templates/eu_accountActivation.vm", context);
			test1.mailm("test-output/report.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}