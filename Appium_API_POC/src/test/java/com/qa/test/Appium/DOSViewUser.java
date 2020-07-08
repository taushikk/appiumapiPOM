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


public class DOSViewUser {
	
	ActionUtility actionUtility;
	ExtentTest test;
	ExcelUtility reader = new ExcelUtility("C:/Users/tkhan/Desktop/Appium_API_POC/Appium_API_POC/src/test/resources/Excel/data.xlsx","sheet1");


	public DOSViewUser() {
		PageFactory.initElements(new AppiumFieldDecorator(TestManager.getDriver()), this);
		this.actionUtility = new ActionUtility();
		this.test= TestManager.getReportLogger();
	}

	@AndroidFindBy(id ="com.example.roche:id/searchview")
	private MobileElement searchView;

	@AndroidFindBy(id ="com.example.roche:id/username")
	private MobileElement userName;

	public void SearchForUser() throws Exception {
		try{
			String userNameValue = reader.getCellDataString(1, 1);
			actionUtility.waitForElementVisible(searchView, 20);			
			searchView.sendKeys(userNameValue);
			Assert.assertTrue(userName.getText().contains(userNameValue));
			test.log(Status.INFO, "Actual is "+userNameValue+"/n Expected is "+userName.getText());
			userName.click();
		}catch(AssertionError ae) {
			test.log(Status.FAIL, "Unable to verify user name");
			throw ae;
		}

	}
	

}
