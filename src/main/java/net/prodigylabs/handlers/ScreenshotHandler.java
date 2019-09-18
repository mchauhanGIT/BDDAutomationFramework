package net.prodigylabs.handlers;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenshotHandler {
	
	protected static WebDriver driver = null;
	
	public ScreenshotHandler(WebDriver webdriver)
	{
		driver = webdriver;
	}	
	
	public String captureScreenShot(String folderName){
		 File destinationPath = null;
	        try {
	    	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    	    String screenshotname = System.currentTimeMillis()+".png";
	    	      boolean dpath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/"+folderName).mkdirs();
	    	     destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/"+folderName+"/"+screenshotname);
	    		  Files.copy(screenshot, destinationPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destinationPath.toString();
	}

}
