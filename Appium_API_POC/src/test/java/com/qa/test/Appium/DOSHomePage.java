package com.qa.test.Appium;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.util.ActionUtility;
import com.qa.util.TestManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DOSHomePage{

	ActionUtility actionUtility;
	ExtentTest test;

	public DOSHomePage() {
		PageFactory.initElements(new AppiumFieldDecorator(TestManager.getDriver()), this);
		this.actionUtility = new ActionUtility();
		this.test = TestManager.getReportLogger();
	}

	@AndroidFindBy(id ="com.example.roche:id/addUser")
	private MobileElement createUserButton;

	@AndroidFindBy(id ="com.example.roche:id/existingUser")
	private MobileElement viewUserButton;

	public void openCreateUserScreen() throws Exception {
		try{
			actionUtility.waitForElementClickable(createUserButton, 20);
			System.out.println(createUserButton.getText());
			createUserButton.click();
			test.log(Status.INFO, "Sucessfully clicked on create user button");
		}catch(Exception e) {
			test.log(Status.FAIL, "Unable to click on create user button");
			throw e;
		}
	}
	

	public void openViewUserScreen() throws Exception {
		try {
			actionUtility.waitForElementClickable(viewUserButton, 20);
			System.out.println(viewUserButton.getText());
			viewUserButton.click();
			test.log(Status.INFO, "Successfully clicked on view user button");
		}catch(Exception e) {
			test.log(Status.FAIL, "Unable to click on view user button");
			throw e;
		}
	}
}
