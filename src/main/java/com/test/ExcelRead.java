package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath;
	DataFormatter formatter = new DataFormatter();

	public ExcelRead() {

		this.xlFilePath = "MailCredentials.xlsx";
		try {
			fis = new FileInputStream(new File(xlFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("Credentials");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getUserName() {
		for (Row singleRow : sheet) {
			for (Cell singleCell : singleRow) {
				String text = formatter.formatCellValue(singleCell);
				if (singleCell.getColumnIndex() == 0 && !text.equalsIgnoreCase("username")) {
					break;
				}
				if (singleCell.getColumnIndex() == 1) {
					return text;
				}
			}
		}
		return null;
	}

	public String getPassword() {
		for (Row singleRow : sheet) {
			for (Cell singleCell : singleRow) {
				String text = formatter.formatCellValue(singleCell);
				if (singleCell.getColumnIndex() == 0 && !text.equalsIgnoreCase("password")) {
					break;
				}
				if (singleCell.getColumnIndex() == 1) {
					return text;
				}
			}
		}
		return null;
	}

}
