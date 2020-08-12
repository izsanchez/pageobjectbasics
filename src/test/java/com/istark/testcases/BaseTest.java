package com.istark.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import com.itstark.base.Page;

public class BaseTest {

	@AfterSuite
	public void tearDown() {

		Page.quit();
		//log.info("Text execution completed");

	}
	
	
}
