package net.prodigylabs.test;

import org.openqa.selenium.WebDriver;
import net.prodigylabs.config.ObjectRepository;

/** @author vaishali.katta *  */
public class BaseTest {
		
	static {
		try {
			ObjectRepository.loadAllProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
