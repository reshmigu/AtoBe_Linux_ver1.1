package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.xrayapis.TestExecution;

public class XrayReport {

	private Workbook workbook = null;
	private Sheet sheet = null;
	DataFormatter formatter = new DataFormatter();


	public XrayReport() {
		workbook = new XSSFWorkbook();
	}

	public void sendReportAsExcel(List<TestExecution> testExecution,String testExecutionKey) throws IOException {
		System.err.println("EXCEL REPORT");
		sheet = workbook.createSheet(testExecutionKey + " execution Result");

		int rowCount = 0;
		rowCount= createRowColumnHeaders(rowCount,testExecutionKey);

		for (TestExecution test : testExecution) {
			Row row = sheet.createRow(rowCount++);

			Cell cell = row.createCell(0);
			cell.setCellValue(test.getKey());

			cell = row.createCell(1);
			cell.setCellValue(test.getStatus());

		}
		FileOutputStream fos = new FileOutputStream(new File(testExecutionKey + " execution Result.xlsx"));
		workbook.write(fos);
		fos.close();
		mail mail = new mail();
		mail.mailm(testExecutionKey + " execution Result.xlsx");
	}

	private int createRowColumnHeaders(int rowCount,String testExecutionKey) {
		Row rowHeader = sheet.createRow(rowCount++);
		Cell cellRowHeader = rowHeader.createCell(0);
		cellRowHeader.setCellValue("Test Execution Result for "+testExecutionKey);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
		Row  columnHeader= sheet.createRow(rowCount++);
		
		Cell cellcolumnHeader = columnHeader.createCell(0);
		cellcolumnHeader.setCellValue("Test case ID");
		
		Cell cellcolumn2Header = columnHeader.createCell(1);
		cellcolumn2Header.setCellValue("Status");
		return rowCount;
		
	}

}
