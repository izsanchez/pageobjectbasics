package com.itstark.base;

import org.openqa.selenium.WebDriver;

import com.itstark.pages.HomePage;
import com.itstark.pages.LoginPage;
import com.itstark.pages.flights.FindFlightPage;

public class LeftMenuWT {

	WebDriver driver;
	public LeftMenuWT(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public FindFlightPage gotoFlights() throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
		Page.click("SearchFlightMenu_XPATH");
		//driver.findElement(By.xpath("//img[@alt='Search Flights Button']")).click();
		
		return new FindFlightPage();
	}

	public HomePage gotoHome() throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
		Page.click("HomeButtonMenu_XPATH");
		//driver.findElement(By.xpath("//img[@alt='Home Button']")).click();
		
		return new HomePage();
	}
	
	public LoginPage SignOff() throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
		Page.click("SignOffMenu_XPATH");
		//driver.findElement(By.xpath("//img[@alt='SignOff Button']")).click();
		
		return new LoginPage();
	}
}
