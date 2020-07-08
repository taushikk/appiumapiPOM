package com.qa.test.Appium;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.util.ActionUtility;
import com.qa.util.ExcelUtility;
import com.qa.util.TestManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DOSCreateUserScreen {

	ActionUtility actionUtility;
	ExcelUtility reader = new ExcelUtility("C:/Users/tkhan/Desktop/Appium_API_POC/Appium_API_POC/src/test/resources/Excel/data.xlsx","sheet1");
	ExtentTest test;
	
	public DOSCreateUserScreen() {
		PageFactory.initElements(new AppiumFieldDecorator(TestManager.getDriver()), this);
		this.test = TestManager.getReportLogger();
		this.actionUtility = new ActionUtility();
	}


	@AndroidFindBy(id ="com.example.roche:id/useridedit")
	private MobileElement userID;

	@AndroidFindBy(id = "com.example.roche:id/firstNameedit")
	private MobileElement firstName;

	@AndroidFindBy(id = "com.example.roche:id/lastNameedit")
	private MobileElement lastName;

	@AndroidFindBy(id = "com.example.roche:id/dobedit")
	private MobileElement DOBCalendar;

	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"01 July 2020\"]")
	private MobileElement date;

	@AndroidFindBy(id="android:id/button1")
	private MobileElement selectButton;

	@AndroidFindBy(id = "com.example.roche:id/emailedit")
	private MobileElement emailAddress;

	@AndroidFindBy(id = "com.example.roche:id/cellnoedit")
	private MobileElement cellNumber;

	@AndroidFindBy(id = "com.example.roche:id/deviceedit")
	private MobileElement deviceID;

	@AndroidFindBy(id = "com.example.roche:id/submitbutton")
	private MobileElement submitButton;

	public void enterUserProfileAttributes() throws Exception {
		try {
			String userIDValue = reader.getCellDataString(1, 0);
			System.out.println(userIDValue);
			actionUtility.waitForElementClickable(userID, 10);
			userID.sendKeys(userIDValue);

			String firstNameValue = reader.getCellDataString(1, 1);
			System.out.println(firstNameValue);
			actionUtility.waitForElementClickable(firstName, 10);
			firstName.sendKeys(firstNameValue);

			String lastNameValue = reader.getCellDataString(1, 2);
			System.out.println(lastNameValue);
			actionUtility.waitForElementClickable(lastName, 10);
			lastName.sendKeys(lastNameValue);

			actionUtility.waitForElementClickable(DOBCalendar, 10);
			DOBCalendar.click();
			actionUtility.verifyIfElementIsDisplayed(date, 20);
			date.click();
			actionUtility.waitForElementClickable(selectButton, 10);
			selectButton.click();

			String mailID = reader.getCellDataString(1, 4);
			System.out.println(mailID);
			actionUtility.waitForElementClickable(emailAddress, 10);
			emailAddress.sendKeys(mailID);

			String cellNumberValue = reader.getCellDataNumber(1, 5);
			System.out.println(cellNumberValue);
			actionUtility.waitForElementClickable(cellNumber, 10);
			cellNumber.sendKeys(cellNumberValue);

			String deviceIDValue = reader.getCellDataNumber(1, 6);
			System.out.println(deviceIDValue);
			actionUtility.scrollDown();
			actionUtility.waitForElementClickable(deviceID, 10);
			deviceID.sendKeys(deviceIDValue);
			test.log(Status.INFO, "Entered all the user attributes");
		}catch(Exception e) {
			test.log(Status.FAIL, "Unable to enter the user attributes");
			throw e;
		}
	}

	public void createUserProfile() throws Exception {
		try{
			actionUtility.scrollDown();
			actionUtility.waitForElementClickable(submitButton, 20);
			submitButton.click();
			test.log(Status.INFO, "Sucessfully created user profile");
		} catch (Exception e) {
			test.log(Status.FAIL, "Unable to create the user profile");
			throw e;
		}	
	}

}