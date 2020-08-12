package com.itstark.pages;

import org.openqa.selenium.By;

import com.itstark.base.Page;

public class HomePage extends Page{

	public void validateHome() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
		isElementPresent(By.xpath(OR.getProperty("msgHome_XPATH")));
		//driver.findElement(By.xpath("//blockquote[contains(text(),'Welcome')]")).isDisplayed();
		
		
	}
}
