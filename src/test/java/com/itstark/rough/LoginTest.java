package com.itstark.rough;




import com.itstark.base.Page;
import com.itstark.pages.HomePage;
import com.itstark.pages.LoginPage;
import com.itstark.pages.PaymentDetails.PaymentDetailsPage;
import com.itstark.pages.flights.FindFlightPage;

public class LoginTest {

	public static HomePage home;
	
	public static void main(String[] args) throws InterruptedException {
		
//		HomePage home  = new HomePage();
//		LoginPage lp = home.gotoLogin();
//		ZooAppPage zp = lp.doLogin("valtymor@hotmail.com", "Slayer2413");
//		zp.gotoCRM();
//		AccountsPage account = Page.menu.gotoAccounts();
//		CreateAccount  cap = account.gotoCreateAccounts();
//		cap.createAccount("Isaac");
		
		LoginPage lp  = new LoginPage();
		home = lp.doLogin("jojo", "bean");
		home.validateHome();
		
		FindFlightPage flight = Page.menu.gotoFlights();
		flight.findFlight();
		PaymentDetailsPage payment = flight.selectFlight();
		payment.setPaymentDetails("AddressTest", "CityTest");
		
		home = Page.menu.gotoHome();
		home.validateHome();
		
		LoginPage logout = Page.menu.SignOff();
		logout.validateLoginPage();
		
		
	}

}
