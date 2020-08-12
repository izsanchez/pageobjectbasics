package com.istark.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.itstark.base.Page;
import com.itstark.pages.PaymentDetails.PaymentDetailsPage;
import com.itstark.pages.flights.FindFlightPage;
import com.itstark.utilities.Utilities;

public class SearchFlight {

	public static FindFlightPage flight;
	
	@Test
	public void searchFligth() throws InterruptedException {
		flight = Page.menu.gotoFlights();
		flight.findFlight();
	
	}
}
