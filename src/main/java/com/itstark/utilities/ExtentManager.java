package com.itstark.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent==null) {
			extent = new ExtentReports("target\\surefire-reports\\html\\extent.html",true,DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File("src\\test\\resources\\com\\itstark\\extentconfig\\ReportsConfig.xml"));
		}
		
		return extent;
	}
	
}
