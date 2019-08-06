package com.test.xrayapis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.time.LocalDateTime;

public class GenerateJasperReport {

	public void createReport(JasperReportDTO jasperReportDTO, List<JasperBugDTO> jasperBugDTOList)
			throws JRException, IOException {
		// Compile jrxml file.
		JasperReport jasperReport = JasperCompileManager
				.compileReport("IssueReport.jrxml");

		// Parameters for report
		int totalTestCases = 0;
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		if (jasperBugDTOList != null && !jasperBugDTOList.isEmpty()) {
			totalTestCases = jasperBugDTOList.size();
		}
		int passCount=(int)jasperBugDTOList.stream().filter(a->a.getTestStatus().equalsIgnoreCase("PASS")).count();
		int failCount=(int)jasperBugDTOList.stream().filter(a->a.getTestStatus().equalsIgnoreCase("FAIL")).count();
		int bugCount=(int)jasperBugDTOList.stream().filter(a->a.getBugLink()!=null).count();
		
		parameters.put("projectName", jasperReportDTO.getProjectName());
		parameters.put("issueId", jasperReportDTO.getIssueId());
		parameters.put("summary", jasperReportDTO.getSummary());
		parameters.put("description", jasperReportDTO.getDescription());
		parameters.put("startedDate", jasperReportDTO.getStartedDate());
		parameters.put("endDate", jasperReportDTO.getEndDate());
		parameters.put("executedBy", jasperReportDTO.getExecutedBy());
		parameters.put("assignee", jasperReportDTO.getAssignee());
		parameters.put("totalTestCases", totalTestCases);
		parameters.put("passCount", passCount);
		parameters.put("failCount", failCount);
		parameters.put("bugCount", bugCount);
		parameters.put("xrayLink", jasperReportDTO.getXrayLink());
		parameters.put("issueLink", jasperReportDTO.getIssueIdLink());


		// parameters.put("SUBREPORT_DIR",
		// "C:/Users/nasia.t/JaspersoftWorkspace/MyReports/");
		/*
		 * jasperBugDTOList.forEach(bugDTO -> { parameters.put("caseId",
		 * bugDTO.getTestCaseId()); parameters.put("testStatus",
		 * bugDTO.getTestStatus()); parameters.put("bug", bugDTO.getLinkedBugId());
		 * 
		 * });
		 */
		/*
		 * jasperBugDTOList.forEach(a->{
		 * System.out.println(a.getBugLink()+"*********************************");
		 * System.out.println(a.getTestCaseId()+"!!!!!!!!!!!!!!"); });
		 */

		JRBeanCollectionDataSource BugList = new JRBeanCollectionDataSource(jasperBugDTOList);
		parameters.put("BugList", BugList);

		// JRBeanCollectionDataSource beanEnvDocument21 = new
		// JRBeanCollectionDataSource(jasperBugDTO);
		// parameters.put("subimages", beanEnvDocument21);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		// Make sure the output directory exists.
		/*
		 * File outDir = new File("C:/jasperoutput"); outDir.mkdirs();
		 */

		// Export to PDF.
		JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
		//JasperExportManager.exportReportToHtmlFile( "report.pdf", "report.html");
		File pdf = new File("report.pdf");
		pdf.mkdirs();

		JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
		System.out.println("Done!");
		System.out.println(LocalDateTime.now()+" ************* ");
	}

}
