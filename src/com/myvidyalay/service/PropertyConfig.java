package com.myvidyalay.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {

	public String getKeyValue(String key){
		
		FileReader reader;
		String value=null;
		try {
			reader = new FileReader("F:\\fileRead\\myvidyalay.properties");
			Properties p=new Properties();
			p.load(reader);
			value = p.getProperty(key);
			
		} catch (FileNotFoundException e) {
			System.out.println("Application is not able to find the file at specified location");
			e.printStackTrace();
		}
		catch (IOException e) {
			
			System.out.println("Key not found please check the key in properties file");
			e.printStackTrace();
		}
		
		return value;
	}
	
	
}
