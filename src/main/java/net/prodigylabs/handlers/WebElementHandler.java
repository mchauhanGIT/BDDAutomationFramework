package net.prodigylabs.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.prodigylabs.config.ObjectRepository;

public class WebElementHandler {
	
	protected WebDriver driver = null;
	WebDriverWait driverWait = null;
	
	public WebElementHandler(WebDriver webdriver)
	{
		driver=webdriver;
	}
	
	public void setDriverWait(String locator) {
		driverWait = new WebDriverWait(driver, ObjectRepository.getLong("global.driver.wait"));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void clickButton(String locator) {
		setDriverWait(locator);
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void enterText(String locator, String text) {
		setDriverWait(locator);
		driver.findElement(By.xpath(locator)).sendKeys(text);
	}
	
	public boolean isDisplayed(String locator) {
		setDriverWait(locator);
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}

}
