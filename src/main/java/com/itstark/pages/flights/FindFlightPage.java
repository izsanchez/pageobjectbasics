package com.itstark.pages.flights;

import org.openqa.selenium.By;

import com.itstark.base.Page;
import com.itstark.pages.PaymentDetails.PaymentDetailsPage;

public class FindFlightPage extends Page {

	public void findFlight() throws InterruptedException {

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
		click("findFlightsBtn_XPATH");
		//driver.findElement(By.xpath("//input[@name='findFlights']")).click();
	}

	public PaymentDetailsPage selectFlight() {

		click("reserveFlightsBtn_XPATH");
		//driver.findElement(By.xpath("//input[@name='reserveFlights']")).click();
		
		return new PaymentDetailsPage();
	}
}
