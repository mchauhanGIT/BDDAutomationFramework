package net.prodigylabs.test;

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
