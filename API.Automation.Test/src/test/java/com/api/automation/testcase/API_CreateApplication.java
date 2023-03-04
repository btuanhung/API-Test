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

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import io.restassured.specification.RequestSpecification;

public class API_CreateApplication extends API_Instance{
	
	@BeforeMethod
	public void setRequest()throws Exception {
		Map<String, Object> job = new HashMap<String, Object>();
		job.put("jobId", PropertiesFileUtils.getProperty("jobId"));

		response = req
				.header("token",ReadAndWriteFile.readToken().getSheet("Token").getRow(1).getCell(0).getStringCellValue())
				//.header("token",PropertiesFileUtils.getToken("token"))
				.body(job)
				.post(PropertiesFileUtils.getProperty("pathCreateApp"));
	}
	@Test
	public void Response() {
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		//verify status code
		assertEquals(response.getStatusCode(), 200);
		
		//verify jobId
		assertTrue(response.asString().contains("jobId"));
		assertEquals(response.jsonPath().get("data.jobId"), PropertiesFileUtils.getProperty("jobId"));
	}
}
