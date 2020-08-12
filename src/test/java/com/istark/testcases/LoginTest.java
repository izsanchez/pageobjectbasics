package com.istark.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.itstark.pages.HomePage;
import com.itstark.pages.LoginPage;
import com.itstark.utilities.Utilities;

public class LoginTest extends BaseTest {
	
	@Test(dataProviderClass= Utilities.class,dataProvider="dp")
	public void loginTest(Hashtable<String,String>data) throws InterruptedException {
		LoginPage lp  = new LoginPage();
		HomePage home = lp.doLogin(data.get("username"), data.get("password"));
		home.validateHome();
	}
}
