package net.prodigylabs.test;

import net.prodigylabs.config.ObjectRepository;

public class BaseTest {
	
	static {
		try {
			ObjectRepository.loadAllProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
