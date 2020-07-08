package com.qa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtility(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getColCount() {
		int colCount = 0;

		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Row count is"+ colCount);
		return colCount;
	}

	public int getRowCount() {
		int rowCount = 0;

		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Row count is"+ rowCount);
		return rowCount;
	}

	public String getCellDataString(int rowNum, int colNum) {
		String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		return cellData;
	}

	public String getCellDataNumber(int rowNum, int colNum) {
		Cell cellData = sheet.getRow(rowNum).getCell(colNum);
		DataFormatter formatter = new DataFormatter();
        String formattedValue = formatter.formatCellValue(cellData);
        return formattedValue;
	}

}
