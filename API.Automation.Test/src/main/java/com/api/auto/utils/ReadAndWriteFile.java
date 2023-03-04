package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteFile {
	
	public static Workbook readFile() throws Exception{
		FileInputStream fis = new FileInputStream("./data/test data.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		return wb;
	}
	
	public static void writeToken(int i, int j, String token) throws Exception{
		FileOutputStream fos = new FileOutputStream("./data/token.xlsx");
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Token");
		Cell cell = sheet.createRow(0).createCell(0);
		cell.setCellValue("token");
		cell = sheet.createRow(i).createCell(j);
		cell.setCellValue(token);
		workbook.write(fos);
		workbook.close();
	}
	public static Workbook readToken() throws Exception{
		FileInputStream fis = new FileInputStream("./data/token.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		return workbook;
	}
}
