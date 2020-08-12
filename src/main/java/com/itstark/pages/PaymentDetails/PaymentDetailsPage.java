package com.itstark.pages.PaymentDetails;

import org.openqa.selenium.By;

import com.itstark.base.Page;

public class PaymentDetailsPage extends Page {

	public void setPaymentDetails(String address, String city) throws InterruptedException {
		Thread.sleep(2000);
		type("addressField_XPATH",address);
		type("cityField_XPATH",city);
		click("buyFlightsButton_XPATH");
//		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
//		driver.findElement(By.xpath("//input[@name='address2']")).sendKeys(city);
//		driver.findElement(By.xpath("//input[@name='buyFlights']")).click();
	}
}
