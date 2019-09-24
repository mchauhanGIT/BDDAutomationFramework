package net.prodigylabs.driver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.prodigylabs.config.ObjectRepository;

/** @author vaishali.katta */
public class CapabilitiesGenerator {
	
	WebDriver driver = null;

	private String browserType = ObjectRepository.getString("global.browser.name");
	
	private CapabilitiesGenerator() {
	}
	
	private static CapabilitiesGenerator instance = new CapabilitiesGenerator();
	 
	
	public static CapabilitiesGenerator getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public WebDriver launchBrowser()
	{
		switch (browserType) {
		case "chrome":
			driver = new ChromeDriver(getbrowserCapabilities(browserType));
			break;
		case "firefox":
			driver = new FirefoxDriver(getbrowserCapabilities(browserType));
			break;
		case "ie":
			driver = new InternetExplorerDriver(getbrowserCapabilities(browserType));
			break;
		default:
			driver = new ChromeDriver(getbrowserCapabilities(browserType));
			break;
		}
		return driver;		
	}
	
	public static DesiredCapabilities getbrowserCapabilities(String browserType) 
	{
		DesiredCapabilities cap = null;		
		
		switch (browserType) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",ObjectRepository.getString("global.browser.chrome.driver.executable"));
			cap = DesiredCapabilities.chrome();
			cap.setCapability("platform",ObjectRepository.getString("global.browser.capability.platform"));
			cap.setBrowserName(ObjectRepository.getString("global.browser.capability.browserName.ch"));
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", ObjectRepository.getString("global.browser.gecko.driver.executable"));
			cap=DesiredCapabilities.firefox();
			cap.setCapability("platform",ObjectRepository.getString("global.browser.capability.platform"));
			cap.setBrowserName(ObjectRepository.getString("global.browser.capability.browserName.ff"));
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", ObjectRepository.getString("global.browser.ie.driver.executable"));
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("platform",ObjectRepository.getString("global.browser.capability.platform"));
			cap.setBrowserName(ObjectRepository.getString("global.browser.capability.browserName.ie"));
		default:
			System.setProperty("webdriver.chrome.driver",ObjectRepository.getString("global.browser.chrome.driver.executable"));
			cap = DesiredCapabilities.chrome();
			cap.setCapability("platform",ObjectRepository.getString("global.browser.capability.platform"));
			cap.setBrowserName(ObjectRepository.getString("global.browser.capability.browserName.ch"));
			break;
		}		
		return cap;
	}

	public WebDriver launchApp(String platformType) throws Exception
	{
		switch (platformType) {
		case "Android":
			DesiredCapabilities getcap = getmobileCapabilities(platformType);
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),getcap);
			break;
		case "iOS":
			driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),getmobileCapabilities(platformType));
			break;
		default:
			
			break;
		}
		return driver;		
	}
	
	public static DesiredCapabilities getmobileCapabilities(String platformType) 
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		
		switch (platformType) {
		case "Android":
	        cap.setCapability("deviceName", ObjectRepository.getString("global.capability.devicename"));
	        cap.setCapability("udid", ObjectRepository.getString("global.capability.deviceid"));               //DeviceId from "adb devices" command
	        
	        cap.setCapability("platformName", ObjectRepository.getString("global.capability.platform"));
	        cap.setCapability("automationName", "UiAutomator2");
	        cap.setCapability("platformVersion", ObjectRepository.getString("global.capability.platform.version"));
	     
	        cap.setCapability("appPackage", ObjectRepository.getString("global.capability.apppackage"));
	        cap.setCapability("appActivity",ObjectRepository.getString("global.capability.appactivity"));
	        
	        cap.setCapability("skipUnlock","true");
	        cap.setCapability("noReset","false");
	        cap.setCapability("unicodeKeyboard", true);
	        cap.setCapability("resetKeyboard", true);
	        cap.setCapability("autoAcceptAlerts", true);
	   		cap.setCapability("autoDismissAlerts", true);		
	   		break;
		case "iOS":

			break;
		default:

			break;
		}		
		return cap;
	}
}
