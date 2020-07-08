package com.qa.test.Appium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.util.ActionUtility;
import com.qa.util.ExcelUtility;
import com.qa.util.TestManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DOSUserDetailsScreen {
	
	ActionUtility actionUtility;
	ExcelUtility reader = new ExcelUtility("C:/Users/tkhan/Desktop/Appium_API_POC/Appium_API_POC/src/test/resources/Excel/data.xlsx","sheet1");
	ExtentTest test;
	
	public DOSUserDetailsScreen() {
		PageFactory.initElements(new AppiumFieldDecorator(TestManager.getDriver()), this);
		this.test = TestManager.getReportLogger();
		this.actionUtility = new ActionUtility();
	}
	
	@AndroidFindBy(id ="com.example.roche:id/username")
	private MobileElement userName;
	
	@AndroidFindBy(id ="com.example.roche:id/useridedit")
	private MobileElement userID;
	
	@AndroidFindBy(id ="com.example.roche:id/usernameedit")
	private MobileElement userNameData;
	
	@AndroidFindBy(id ="com.example.roche:id/userEmailedit")
	private MobileElement emailData;
	
	@AndroidFindBy(id ="com.example.roche:id/userDateofbirthedit")
	private MobileElement DOBData;
	
	public void verifyUserDetails() throws Exception {
		try {
			String userIDExpected = reader.getCellDataString(1, 0);
			String firstNameExpected = reader.getCellDataString(1, 1);
			String mailIDExpected = reader.getCellDataString(1, 4);
			
			Assert.assertEquals(userID.getText(), userIDExpected);
			Assert.assertTrue(userNameData.getText().contains(firstNameExpected));
			Assert.assertEquals(emailData.getText(), mailIDExpected);
			//Assert.assertEquals(DOBData.getText(), "2000-07-07");	
			test.log(Status.INFO, "Verified the user Details");
		}catch(AssertionError ae) {
			test.log(Status.FAIL, "Unable to verify the user Details");
			throw ae;
		}
	}

}
