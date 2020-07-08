package com.qa.testDefinition;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.PageObjects;

@Listeners(com.qa.custom_listener.TestExecution.class)
public class TestUsercreation {
	
	@Parameters({"deviceId","deviceName","portNumber"})
	@Test
	public void TestCase_one_verifyUserIsAbleTocreateUserProfile() throws Exception {
		PageObjects pagePOC = new PageObjects();
		pagePOC.homepage.openCreateUserScreen();
		pagePOC.createuser.enterUserProfileAttributes();
		pagePOC.createuser.createUserProfile();	
	}
    
	@Parameters({"deviceId","deviceName","portNumber"})
	@Test
	public void TestCase_two_viewUserProfileAndVerifyDetails() throws Exception {
		PageObjects pagePOC = new PageObjects();
		pagePOC.homepage.openViewUserScreen();
		pagePOC.viewuser.SearchForUser();
		pagePOC.userDetails.verifyUserDetails();

	}

}
