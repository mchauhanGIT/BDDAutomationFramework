package net.prodigylabs.config;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;

/** @author vaishali.katta */

public class ObjectRepository {

	private static CombinedConfiguration propAggregator = new CombinedConfiguration();
	 
	 private static final String ALL_PROP = "allProperties.properties";	 
 
	public static void loadAllProperties() throws Exception {
			Properties propFilesList = new Properties();
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();		
			try {
				URL url = loader.getResource(ALL_PROP);
				propFilesList.load(new FileReader(url.getPath()));
			       System.out.println("List of Files Loaded " +propFilesList.getProperty("propFiles") );
			} catch (IOException e) {
				throw new IOException("Unable to load all properties file", e);
			}
			String allprop = propFilesList.getProperty("propFiles");

			Set<String> set = new HashSet<String>(Arrays.asList(allprop.split(",")));
			
			Iterator<String> fileIterator = set.iterator();
			
			while (fileIterator.hasNext()) 
			{
	            System.out.println("Next"); 
	            appendProperties(fileIterator.next());
			}
	}
	
	public static void appendProperties(String propertiesFile) throws Exception {
		
		PropertiesConfiguration properties = null;
		try {
			properties = new PropertiesConfiguration(propertiesFile);
			if (properties!=null) {
				propAggregator.append(properties);
			}
			
		} catch (ConfigurationException ce) {
			throw new Exception("Unable to append properties files", ce);
		}		
	}
	
	public static String getString(String key) {
		return propAggregator.getString(key);
	}
	
	public static long getLong(String key) {
		return propAggregator.getLong(key);
	}
	
	public static By getobjectLocator(String locatorName)
	 {
		String locatorProperty = propAggregator.getString(locatorName);
		// System.out.println(locatorProperty.toString());
		String locatorType = locatorProperty.split(":")[0];
		String locatorValue = locatorProperty.split(":")[1];
	 
		By locator = null;
		switch(locatorType)
		{
			case "Id":
				locator = By.id(locatorValue);
				break;
			case "Name":
				locator = By.name(locatorValue);
				break;
			case "CssSelector":
				locator = By.cssSelector(locatorValue);
				break;
			case "LinkText":
				locator = By.linkText(locatorValue);
				break;
			case "PartialLinkText":
				locator = By.partialLinkText(locatorValue);
				break;
			case "TagName":
				locator = By.tagName(locatorValue);
				break;
			case "Xpath":
				locator = By.xpath(locatorValue);
				break;
			case "className":
				locator = By.className(locatorValue);
				break;
		}
		return locator;
	 }
}
