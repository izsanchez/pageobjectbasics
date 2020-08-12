package com.itstark.pages;

import org.openqa.selenium.By;

import com.itstark.base.Page;

public class LoginPage extends Page {

	public HomePage doLogin(String username, String password) {
		
		driver.switchTo().frame("body").switchTo().frame("navbar");
		type("userField_XPATH",username);
		type("pwdField_XPATH",password);
		click("loginBtn_XPATH");
		
		//driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@name='login']")).click();
		
		return new HomePage();
	}
	
	public void validateLoginPage() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
		isElementPresent(By.xpath(OR.getProperty("msgLogi_XPATH")));
		
		//driver.findElement(By.xpath("//b[contains(text(),'Web Tours')]")).isDisplayed();
		
	}
	
}
