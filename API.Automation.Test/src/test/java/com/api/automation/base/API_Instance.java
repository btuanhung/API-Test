package com.api.automation.base;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Instance {
	protected RequestSpecification req;
	protected Response response;
	
	@BeforeClass
	public void init() {
		baseURI = PropertiesFileUtils.getProperty("base_url");
		req = given().contentType(ContentType.JSON);
	}
}
