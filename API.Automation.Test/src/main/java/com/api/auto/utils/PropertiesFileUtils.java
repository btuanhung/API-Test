package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	private static String configPath = "./configuration/config.properties";
	private static String tokenPath = "./configuration/tokens.properties";
	
	public static String getProperty(String key) {
		Properties prop = new Properties();
		String value = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configPath);
			prop.load(fis);
			value = prop.getProperty(key);
			return value;
		}catch(Exception ex) {
			System.out.println("Đã xảy ra lỗi khi lấy giá trị " + key);
			ex.printStackTrace();
		}
		return value;
	}
	
	public static void setProperty(String key, String value) {
		Properties prop = new Properties();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(configPath);
			prop.setProperty(key, value);
			prop.store(fos, value);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void saveToken(String key, String value) {
		Properties prop = new Properties();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(tokenPath);
			prop.setProperty(key, value);
			prop.store(fos, value);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getToken(String key) {
		Properties prop = new Properties();
		String value = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(tokenPath);
			prop.load(fis);
			value = prop.getProperty(key);
			return value;
		}catch(Exception ex) {
			System.out.println("Đã xảy ra lỗi khi lấy giá trị " + key);
			ex.printStackTrace();
		}
		return value;
	}
}
