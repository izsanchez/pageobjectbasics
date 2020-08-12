package com.istark.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.itstark.base.Page;
import com.itstark.pages.PaymentDetails.PaymentDetailsPage;
import com.itstark.utilities.Utilities;

public class PaymentDetails extends Page {

	@Test(dataProviderClass= Utilities.class,dataProvider="dp")
	public void paymentDetails(Hashtable<String,String>data) throws InterruptedException {
		PaymentDetailsPage payment = SearchFlight.flight.selectFlight();
		payment.setPaymentDetails(data.get("address"), data.get("city"));
		Assert.fail("Payment Details Failed");
	}
}
