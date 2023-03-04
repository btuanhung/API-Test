package com.api.automation.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;
import com.api.auto.utils.ReadAndWriteFile;
import com.api.automation.base.API_Instance;

import io.restassured.specification.RequestSpecification;

public class API_CreateWork extends API_Instance {
	ReadAndWriteFile read = new ReadAndWriteFile();
	@BeforeMethod
	public void setRequest() throws Exception {
		String nameWork = read.readFile().getSheet("Create_Work").getRow(1).getCell(0).getStringCellValue();
		String experience = read.readFile().getSheet("Create_Work").getRow(1).getCell(1).getStringCellValue();
		String education = read.readFile().getSheet("Create_Work").getRow(1).getCell(2).getStringCellValue();
		String token = read.readToken().getSheet("token").getRow(1).getCell(0).getStringCellValue();
		//String token = PropertiesFileUtils.getProperty("token");
		Map<String, Object> work = new HashMap<String, Object>();
		work.put("nameWork", nameWork);
		work.put("experience", experience);
		work.put("education", education);
		
		/*work.put("nameWork", PropertiesFileUtils.getProperty("nameWork"));
		work.put("experience", PropertiesFileUtils.getProperty("experience"));
		work.put("education", PropertiesFileUtils.getProperty("education"));*/
		
		response = req
				.header("token",token)
				.body(work)
				.post(PropertiesFileUtils.getProperty("cre_work_path"));
	}
	@Test
	public void CreateWorkResponse() {
		//check response
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		//verify status code
		assertEquals(response.getStatusCode(), 201);
		
		//infomation verification
		assertTrue(response.asString().contains("id"));
		assertTrue(response.asString().contains("nameWork"));
		assertTrue(response.asString().contains("experience"));
		assertTrue(response.asString().contains("education"));
		
		//validate information
		assertEquals(response.jsonPath().get("nameWork"), PropertiesFileUtils.getProperty("nameWork"));
		assertEquals(response.jsonPath().get("experience"), PropertiesFileUtils.getProperty("experience"));
		assertEquals(response.jsonPath().get("education"), PropertiesFileUtils.getProperty("education"));
	}

}
