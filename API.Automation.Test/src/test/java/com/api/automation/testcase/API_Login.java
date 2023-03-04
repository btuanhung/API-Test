package com.api.automation.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;
import com.api.auto.utils.ReadAndWriteFile;
import com.api.automation.base.API_Instance;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

public class API_Login extends API_Instance {
	String msg = "Đăng nhập thành công";
	ReadAndWriteFile read = new ReadAndWriteFile();
	@BeforeMethod
	public void setRequest()throws Exception {
		String acc = read.readFile().getSheet("Login_data").getRow(0).getCell(0).getStringCellValue();
		String pass = read.readFile().getSheet("Login_data").getRow(0).getCell(1).getStringCellValue();
		
		/*
		String acc = PropertiesFileUtils.getProperty("account");
		String pass = PropertiesFileUtils.getProperty("password");
		*/
		Map<String, Object> account = new HashMap<String, Object>();
		account.put("account", acc);
		account.put("password", pass);
		
		response = req.body(account).post(PropertiesFileUtils.getProperty("pathLogin"));
	}
	@Test
	public void checkLoginResponse()throws Exception {
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		// verify Status Code
		assertEquals(response.getStatusCode(), 200);
	
		// verify Message
		assertTrue(response.asString().contains("message"));
		assertEquals(response.jsonPath().get("message"), msg);
	
		//verify Token
		assertTrue(response.asString().contains("token"));
		
		//save token
		String token = response.jsonPath().get("token");
		read.writeToken(1, 0, token);
		//PropertiesFileUtils.saveToken("token", token);
		
		// check User Infomation
		assertTrue(response.asString().contains("account"));
		assertTrue(response.asString().contains("password"));
		assertTrue(response.asString().contains("type"));
		
		//verify User Infomation
		assertEquals(response.jsonPath().get("user.account"), PropertiesFileUtils.getProperty("account"));
		assertEquals(response.jsonPath().get("user.password"), PropertiesFileUtils.getProperty("password"));
		assertEquals(response.jsonPath().get("user.type"), PropertiesFileUtils.getProperty("type"));
	}
	
}
