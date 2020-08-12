package com.istark.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.itstark.base.Page;
import com.itstark.pages.HomePage;
import com.itstark.pages.LoginPage;
import com.itstark.utilities.Utilities;

public class SignOff extends Page {


	
	//@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	@Test
	public void signOff() throws InterruptedException {
		
		HomePage home = Page.menu.gotoHome();
		home.validateHome();
		LoginPage logout = Page.menu.SignOff();
		logout.validateLoginPage();

		
	}
}
