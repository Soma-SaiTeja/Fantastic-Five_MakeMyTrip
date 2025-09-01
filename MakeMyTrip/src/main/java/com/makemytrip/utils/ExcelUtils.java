package com.makemytrip.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils{
	
	String fileName;
	FileInputStream fis;
	FileOutputStream fos;
	Workbook wb;
	Sheet sheet;
	
	public List<HashMap<String, String>> data(String filePath, String sheetName) throws IOException {

		List<HashMap<String, String>> mydata = new ArrayList<>();

		FileInputStream fis = new FileInputStream(ConfigReader.getProperty("test.data.file"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();

		XSSFRow row = sheet.getRow(0);

		for (int i = 1; i <= totalRows; i++) {
			XSSFRow currRow = sheet.getRow(i);

			HashMap<String, String> table = new HashMap<>();

			for (int j = 0; j < currRow.getLastCellNum(); j++) {
				XSSFCell currCell = currRow.getCell(j);
				table.put(row.getCell(j).toString(), currCell.toString());
			}
			mydata.add(table);
		}
		workbook.close();
		fis.close();
		
		return mydata;
	}
	
	
}