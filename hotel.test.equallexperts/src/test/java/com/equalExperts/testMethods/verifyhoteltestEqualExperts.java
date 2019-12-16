package com.equalExperts.testMethods;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.equalExperts.pageObjects.HotelBookingFormPage;
import com.equalExperts.testBase.TestBase;

/**
 * 
 * @author Pankaj Kumar
 *
 */
public class verifyhoteltestEqualExperts extends TestBase {

	// Create an Instance for Login Page
	HotelBookingFormPage hotelBookingFormPage;

	public verifyhoteltestEqualExperts() {
		super();
	}

	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			initialize();
			hotelBookingFormPage = new HotelBookingFormPage(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * test Data for test Method
	 * 
	 * @return
	 */

	@DataProvider(name = "savehoteldata")
	public String[][] savehoteldata() {
		String[][] testdata = getData("TestData.xlsx", "savedata");
		return testdata;
	}

	@DataProvider(name = "invalidpassword")
	public String[][] invalidPwd() {
		String[][] testdata = getData("TestData.xlsx", "invalidpassword");
		return testdata;
	}

	/**
	 * Scenario - VerifyHotelBookingFormWithValidCredentials-----
	 * 
	 * @param title
	 * @param header
	 * @param fnamelbl
	 * @param firstnametxt
	 * @param surnamelbl
	 * @param surnametxt
	 * @param pricelbl
	 * @param pricetxt
	 * @param depositlbl
	 * @param deposittxt
	 * @param checkinlbl
	 * @param checkinDate
	 * @param checkoutlbl
	 * @param checkoutdate
	 * @throws InterruptedException
	 */
	@Test(priority = 0, dataProvider = "savehoteldata")
	public void TS01_VerifyHotelBookingFormWithValidCredentials(String title, String header, String fnamelbl,
			String firstnametxt, String surnamelbl, String surnametxt, String pricelbl, String pricetxt,
			String depositlbl, String deposittxt, String checkinlbl, String checkinDate, String checkoutlbl,
			String checkoutdate) throws InterruptedException {
		test = extent.startTest("TS01_verify Hotel booking form-save functionality with valid data");

		HotelBookingFormPage.IsHotelBookingFormTitleDisplayed(title);
		HotelBookingFormPage.IsHotelBooingFormHeaderDisplayed(header);

		HotelBookingFormPage.IsFirstNameLabelDisplayed(fnamelbl);
		firstnametxt = firstnametxt + " " + getRandomString();
		HotelBookingFormPage.IsUserAbletoEnterFirstName(firstnametxt);
		Thread.sleep(3000);
		HotelBookingFormPage.IsSurNameLabelDisplayed(surnamelbl);
		surnametxt = surnametxt + " " + getRandomString();
		HotelBookingFormPage.IsUserAbletoEnterSurName(surnametxt);

		HotelBookingFormPage.IsPriceLabelDisplayed(pricelbl);
        pricetxt = pricetxt + getRandomNumber();
        System.out.println("Price Values "+ pricetxt);
		HotelBookingFormPage.IsUserAbletoEnterPrice(pricetxt);
		Thread.sleep(3000);
		HotelBookingFormPage.IsDepositLabelDisplayed(depositlbl);
		
		HotelBookingFormPage.IsUserAbletoEnterDeposit(deposittxt);

		HotelBookingFormPage.IsCheckInLabelDisplayed(checkinlbl);
		HotelBookingFormPage.IsUserAbletoEnterCheckInDate(checkinDate);

		HotelBookingFormPage.IsCheckOutLabelDisplayed(checkoutlbl);
		HotelBookingFormPage.IsUserAbletoEnterCheckOut(checkoutdate);
		Thread.sleep(3000);
		HotelBookingFormPage.ClickSavebutton();
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfully(firstnametxt));
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfully(surnametxt));
		
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfullyForPrice(pricetxt));
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfully(deposittxt));
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfully(checkinDate));
		Assert.assertTrue(HotelBookingFormPage.IsSavedDataDisplayedSuccessfully(checkoutdate));
		Thread.sleep(3500);
		// Delete record
		HotelBookingFormPage.IsUserAbleToDeleteRecordFromHotelBookingList(firstnametxt);

	}

	@AfterMethod
	public void takeSnapShot(ITestResult result) throws Exception {
		getResult(result);
	}

	@AfterTest
	public void quit() {
		driver.close();
		extent.endTest(test);
		extent.flush();
		extent.close();
		System.out.println("End");
	}
}
