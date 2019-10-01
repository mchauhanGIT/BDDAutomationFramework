package net.prodigylabs.handlers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.prodigylabs.config.ObjectRepository;

/** @author vaishali.katta */
public class WebElementHandler {
	
	protected WebDriver driver = null;
	WebDriverWait driverWait = null;
	private boolean flag = false;
	int count = ObjectRepository.getInt("global.driver.interate");
	JavascriptExecutor jse = null;
	
	public WebElementHandler(WebDriver webdriver)
	{
		driver=webdriver;
		jse = (JavascriptExecutor)driver;
	}
	
	public void setDriverWait(String locator) {
		driverWait = new WebDriverWait(driver, ObjectRepository.getLong("global.driver.wait"));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void setDriverWait(By locator) {
		driverWait = new WebDriverWait(driver, ObjectRepository.getLong("global.driver.wait"));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void clickButton(String locator) {
		setDriverWait(locator);
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void clickButton(By locator) {
		setDriverWait(locator);
		driver.findElement(locator).click();
	}
	
	public void enterText(String locator, String text) {
		setDriverWait(locator);
		driver.findElement(By.xpath(locator)).sendKeys(text);
	}
	
	public void enterText(By locator, String text) throws Exception {
		setDriverWait(locator);
		WebElement we = driver.findElement(locator);
		if (we!=null) {
			setDriverWait(locator);
			we.click();
			we.sendKeys(text);
		}		
	}
	
	public void enterTextUsingJavascript(By locator, String text) throws Exception {
		setDriverWait(locator);
		WebElement we = driver.findElement(locator);
		if (we!=null) {
			setDriverWait(locator);
			we.click();
			jse.executeScript("arguments[0].value='"+text+"';", we);
		}		
	}
	
	public String getText(By locator) {
		setDriverWait(locator);
		String text = driver.findElement(locator).getText();
		return text;
	}
	
	public boolean isDisplayed(String locator) {
		setDriverWait(locator);
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	public boolean isDisplayed(By locator) throws Exception {
		setDriverWait(locator);		
		WebElement we = driver.findElement(locator);
		if (we!=null) {
			for(int i=0; i<=count;i++){
				  try{
				     flag = we.isDisplayed();
				     break;
				  }
				  catch(Exception e){
					     throw new Exception("ELement is not displayed "+locator);
					  }
				  }
		}
		return flag;
	}
	
	public Select getDropDown(By locator) throws Exception {
		Select dropDown = null;
		setDriverWait(locator);
		dropDown = new Select(driver.findElement(locator));
		return dropDown;
	}
	
	public void selectByVisibleText(Select dropDown, String visibleText) throws Exception {
		if (dropDown != null) {
			dropDown.selectByVisibleText(visibleText);
		} else {
			throw new Exception("Unable to locate dropdown element");
		}
	}
	
	public void selectByValue(Select dropDown, String value) throws Exception {
		if (dropDown != null) {
			dropDown.selectByValue(value);
		} else {
			throw new Exception("Unable to locate dropdown element");
		}
	}
	
	public void selectByIndex(Select dropDown, int index) throws Exception {
		if (dropDown != null) {
			dropDown.selectByIndex(index);
		} else {
			throw new Exception("Unable to locate dropdown element");
		}
	}
	
	public void waitforinvisibilityofElement(By locator) throws Exception {
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void clickElementByText(By locator, String text) throws Exception {
		setDriverWait(locator);
		List<WebElement> allelements = driver.findElements(locator);
		for (WebElement element : allelements) {
			if (element.getText()!=null && element.getText().contains(text)) {
				element.click();
				break;
			}
		}		
	}
}
