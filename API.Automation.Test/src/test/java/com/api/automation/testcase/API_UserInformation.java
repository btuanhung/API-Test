package com.api.automation.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;
import com.api.auto.utils.ReadAndWriteFile;
import com.api.automation.base.API_Instance;

public class API_UserInformation extends API_Instance{
	@BeforeMethod
	public void setRequest()throws Exception {
		response = req
				.headers("token",ReadAndWriteFile.readToken().getSheet("Token").getRow(1).getCell(0).getStringCellValue())
				//.header("token",PropertiesFileUtils.getToken("token"))
				.get(PropertiesFileUtils.getProperty("pathInfo"));
	}
	@Test
	public void Response() {
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		//verify status code
		assertEquals(response.getStatusCode(), 200);
	}
}
